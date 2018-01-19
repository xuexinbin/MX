/* WebUploader */
if (WebUploader) {
    var initWebUploader = function($element, index) {
        var old = $element.data('webuploader'), options = $element.data('options')

        if (old) {
            old.destroy()
            $element.data('webuploader.wrap').remove()
        }

        if (options) {
            if (typeof options === 'string') {
                options = options.trim().toObj()
            }

            if (typeof options === 'object') {
                $element.hide()

                var $wrap = $('<div id="uploader" class="wu-example"><div class="queueList"><div id="dndArea" class="placeholder"><div id="filePicker"></div><p>或将文件拖到这里</p></div></div><div class="statusBar" style="display:none;"><div class="progress"><span class="text">0%</span><span class="percentage"></span></div><div class="info"></div><div class="btns"><div id="filePicker2"></div><div class="uploadBtn">开始上传</div></div></div>'),
                    // 图片容器
                    $queue = $('<ul class="filelist"></ul>').appendTo($wrap.find('.queueList')),
                    // 状态栏，包括进度和控制按钮
                    $statusBar = $wrap.find('.statusBar'),
                    // 文件总体选择信息。
                    $info = $statusBar.find('.info'),
                    // 上传按钮
                    $upload = $wrap.find('.uploadBtn'),
                    // 没选择文件之前的内容。
                    $placeHolder = $wrap.find('.placeholder'),
                    // 总体进度条
                    $progress = $statusBar.find('.progress').hide(),
                    // 添加的文件数量
                    fileCount = 0,
                    // 添加的文件总大小
                    fileSize = 0,
                    // 优化retina, 在retina下这个值是2
                    ratio = window.devicePixelRatio || 1,
                    // 缩略图大小
                    thumbnailWidth = 110 * ratio,
                    thumbnailHeight = 110 * ratio,
                    // 可能有pedding, ready, uploading, confirm, done.
                    state = 'pedding',
                    // 所有文件的进度信息，key为file id
                    percentages = {},
                    supportTransition = (function() {
                        var s = document.createElement('p').style,
                            r = 'transition' in s ||
                                'WebkitTransition' in s ||
                                'MozTransition' in s ||
                                'msTransition' in s ||
                                'OTransition' in s

                        s = null

                        return r
                    })(),
                    // 图片访问基地址
                    basePath = options.basePath || '',
                    // WebUploader实例
                    uploader,
                    // 上传文件的单位(单位 + 类型)
                    upunit = options.upunit || '张图片'

                // 当有文件添加进来时执行，负责view的创建
                var addFile = function(file, isuploaded, _index) {
                    if (!file && options.uploaded) {
                        $.each(options.uploaded.split(','), function(i, n) {
                            var uploadedFile = {id:'WU_FILE_UP_'+ i, name:n.trim(), src:basePath + n.trim()}

                            addFile(uploadedFile, true, i)

                            fileCount++
                        })

                        if (fileCount) {
                            $placeHolder.addClass('element-invisible');
                            $statusBar.show()
                            setState('uploaded')
                        }

                        return
                    }

                    if (fileCount >= options.fileNumLimit) {
                        $statusBar.find('#filePicker2').hide()
                    }

                    var uploadedAttr = isuploaded && upunit === '张图片' ? ' style="cursor:pointer;" data-toggle="dialog" data-options="{id:\'bjui-dialog-view-upload-image\', image:\''+ encodeURIComponent(file.src) +'\', width:800, height:500, mask:true, title:\'查看已上传图片\'}"' : '',
                        $li = $('<li class="'+ (isuploaded ? 'uploaded' : '') +'" id="'+ file.id +'_'+ index +'">' +
                            '<p class="title">' + file.name + '</p>' +
                            '<p class="imgWrap" '+ uploadedAttr +'></p>'+
                            '<p class="progress"><span></span></p>' +
                            '</li>'),
                        $btns = $('<div class="file-panel">' +
                            '<span class="cancel">删除</span>' +
                            '<span class="rotateRight">向右旋转</span>' +
                            '<span class="rotateLeft">向左旋转</span></div>').appendTo($li),
                        $prgress = $li.find('p.progress span'),
                        $imgWrap = $li.find('p.imgWrap'),
                        $info = $('<p class="error"></p>'),
                        text = '',
                        showError = function(code) {
                            switch(code) {
                                case 'exceed_size':
                                    text = '文件大小超出'

                                    break
                                case 'interrupt':
                                    text = '上传暂停'

                                    break
                                default:
                                    text = '上传失败，请重试'

                                    break
                            }

                            $info.text(text).appendTo($li)
                        }

                    if (!isuploaded) {
                        if (file.getStatus() === 'invalid') {
                            showError(file.statusText)
                        } else {
                            // @todo lazyload
                            $imgWrap.text('预览中')
                            uploader.makeThumb(file, function(error, src) {
                                if (error) {
                                    $imgWrap.text('不能预览')
                                    return
                                }

                                var img = $('<img src="'+src+'">')

                                $imgWrap.empty().append(img)
                            }, thumbnailWidth, thumbnailHeight)

                            percentages[file.id] = [file.size, 0]
                            file.rotation = 0
                        }

                        file.on('statuschange', function(cur, prev) {
                            if (prev === 'progress') {
                                $prgress.hide().width(0)
                            } else if (prev === 'queued') {

                            }

                            // 成功
                            if (cur === 'error' || cur === 'invalid') {
                                showError(file.statusText)
                                percentages[file.id][1] = 1
                            } else if (cur === 'interrupt') {
                                showError('interrupt')
                            } else if (cur === 'queued') {
                                percentages[file.id][1] = 0
                            } else if (cur === 'progress') {
                                $info.remove()
                                $prgress.css('display', 'block')
                            } else if (cur === 'complete') {
                                $li.append('<span class="success"></span>')
                            }

                            $li.removeClass('state-' + prev).addClass('state-' + cur)
                        })
                    } else {
                        $imgWrap.empty().append('<img src="'+ file.src +'">')
                        if (options.initUploaded) {
                            var arr = options.initUploaded.split(',')

                            $li.append('<input type="hidden" class="upload" name="'+ (options.upname || $element.data('name')) +'" value="'+ arr[_index] +'">')
                        }
                    }

                    $li.on('mouseenter', function() {
                        $btns.stop().animate({height: 30})
                    })

                    $li.on('mouseleave', function() {
                        $btns.stop().animate({height: 0})
                    })

                    $btns.on('click', 'span', function() {
                        var index = $(this).index(),
                            deg

                        switch (index) {
                            case 0:
                                if (isuploaded) {
                                    fileCount --
                                    removeFile(file)

                                    if (!fileCount) {
                                        setState('pedding');
                                    }
                                    uploader.refresh()
                                    updateTotalProgress()
                                } else {
                                    uploader.removeFile(file)
                                }

                                return
                            case 1:
                                file.rotation += 90

                                break
                            case 2:
                                file.rotation -= 90

                                break
                        }

                        if (supportTransition) {
                            deg = 'rotate(' + file.rotation + 'deg)'
                            $imgWrap.css({
                                '-webkit-transform': deg,
                                '-mos-transform': deg,
                                '-o-transform': deg,
                                'transform': deg
                            })
                        } else {
                            $imgWrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')')
                        }
                    })

                    $li.appendTo($queue)
                }

                // 负责view的销毁
                var removeFile = function(file) {
                    var $li = $wrap.find('#'+ file.id +'_'+ index)

                    delete percentages[file.id]
                    updateTotalProgress()

                    $li.off().find('.file-panel').off().end().remove()

                    if (fileCount < options.fileNumLimit) {
                        $statusBar.find('#filePicker2').show()
                    }
                }

                var updateTotalProgress = function() {
                    var loaded = 0,
                        total  = 0,
                        spans  = $progress.children(),
                        percent

                    $.each(percentages, function(k, v) {
                        total  += v[0]
                        loaded += v[0] * v[1]
                    })

                    percent = total ? loaded / total : 0

                    spans.eq(0).text(Math.round(percent * 100) + '%')
                    spans.eq(1).css('width', Math.round(percent * 100) + '%')
                    updateStatus()
                }

                var updateStatus = function() {
                    var text = '', stats;

                    if (state === 'ready') {
                        text = '选中'+ fileCount + upunit + '，共'+ WebUploader.formatSize(fileSize) +'。'
                    } else if (state === 'confirm') {
                        stats = uploader.getStats()
                        if (stats.uploadFailNum) {
                            text = '已成功上传'+ stats.successNum + upunit +'，'+
                                stats.uploadFailNum + upunit +'上传失败，<a class="retry" href="#">重新上传</a> 或 <a class="ignore" href="#">忽略</a>'
                        }

                    } else if (state === 'uploaded') {
                        text = '已上传'+ fileCount + upunit
                    } else {
                        stats = uploader.getStats()
                        text = '共'+ fileCount + upunit +'（' + WebUploader.formatSize(fileSize) +'），已上传' + stats.successNum

                        if (stats.uploadFailNum) {
                            text += '，失败' + stats.uploadFailNum
                        }
                    }

                    $info.html(text)
                    $element.data('fileCount', fileCount)
                }

                var setState = function(val) {
                    var file, stats

                    if (val === state) {
                        return
                    }

                    $upload.removeClass('state-' + state)
                    $upload.addClass('state-' + val)
                    state = val

                    switch (state) {
                        case 'pedding':
                            $placeHolder.removeClass('element-invisible')
                            $queue.parent().removeClass('filled')
                            $queue.hide();
                            $statusBar.addClass('element-invisible')
                            uploader.refresh()

                            break
                        case 'ready':
                            $placeHolder.addClass('element-invisible')
                            $wrap.find('#filePicker2').removeClass('element-invisible')
                            $queue.parent().addClass('filled')
                            $queue.show()
                            $statusBar.removeClass('element-invisible')
                            uploader.refresh()
                            $upload.removeClass('disabled')

                            break
                        case 'uploading':
                            $wrap.find('#filePicker2').addClass('element-invisible')
                            $progress.show()
                            $upload.text('暂停上传')

                            break
                        case 'paused':
                            $progress.show()
                            $upload.text('继续上传')

                            break
                        case 'confirm':
                            $progress.hide()
                            $upload.text('开始上传').addClass('disabled')

                            stats = uploader.getStats()
                            if (stats.successNum && !stats.uploadFailNum) {
                                setState('finish')
                                return
                            }

                            break
                        case 'finish':
                            stats = uploader.getStats()
                            if (stats.successNum) {

                            } else {
                                // 没有成功的图片，重设
                                state = 'done'

                                BJUI.alertmsg('info', '上传失败！')
                            }

                            break
                        case 'uploaded':
                            $upload.text('开始上传').addClass('disabled')

                            break
                    }

                    if (state !== 'uploaded')
                        updateStatus()
                }

                $wrap.insertAfter($element)

                if (!WebUploader.Uploader.support()) {
                    alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
                    throw new Error('WebUploader does not support the browser you are using.');
                }

                // 是否允许重新上传
                if (typeof options.reupload === 'undefined')
                    options.reupload = true

                options = $.extend(true, {}, {
                    pick: {
                        id: $wrap.find('#filePicker'),
                        label: '点击选择图片'
                    },
                    dnd: $wrap.find('.queueList'),
                    paste: false,
                    accept: {
                        title: 'Images',
                        extensions: 'gif,jpg,jpeg,bmp,png',
                        mimeTypes: 'image/*'
                    },
                    swf: BJUI.PLUGINPATH + 'webuploader/Uploader.swf', // swf文件路径
                    disableGlobalDnd: false,
                    chunked: false,
                    server: null,
                    fileNumLimit: 300,
                    fileSizeLimit: 200 * 1024 * 1024,        // 200 M
                    fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
                }, options)

                // 实例化
                uploader = WebUploader.create(options)

                // 如果有已上传的图片(编辑时)
                if (typeof $element.data('uploaded') !== 'undefined')
                    options.uploaded = $element.data('uploaded')
                if (options.uploaded) {
                    // 将已上传图片加到队列
                    addFile()

                    $queue.parent().addClass('filled')
                }

                // 上传成功
                uploader.on('uploadSuccess', function(file, response) {
                    if (response[BJUI.keys.statusCode] != BJUI.statusCode.ok) {
                        BJUI.alertmsg('error', response.message)
                    } else {
                        var $li = $wrap.find('#'+ file.id +'_'+ index)

                        $li.find('input.upload').remove().end()
                            .append('<input type="hidden" class="upload" name="'+ (options.upname || $element.data('name')) +'" value="'+ response.filename +'">')
                    }
                })
                // 上传失败
                uploader.on('uploadError', function(file, response) {
                    BJUI.alertmsg('error', response.message)
                })

                // 添加“添加文件”的按钮，
                if (options.fileNumLimit > 1) {
                    uploader.addButton({
                        id: $wrap.find('#filePicker2'),
                        label: '继续添加'
                    })

                    if (fileCount >= options.fileNumLimit)
                        $statusBar.find('#filePicker2').hide()
                }

                uploader.onUploadProgress = function(file, percentage) {
                    var $li = $wrap.find('#'+ file.id +'_'+ index),
                        $percent = $li.find('.progress span')

                    $percent.css('width', percentage * 100 + '%')
                    percentages[file.id][1] = percentage
                    updateTotalProgress()
                }

                uploader.onFileQueued = function(file) {
                    fileCount++
                    fileSize += file.size

                    if (fileCount === 1) {
                        $placeHolder.addClass('element-invisible');
                        $statusBar.show()
                    }

                    addFile(file)
                    setState('ready')
                    updateTotalProgress()
                }

                uploader.onFileDequeued = function(file) {
                    fileCount--
                    fileSize -= file.size

                    if (!fileCount) {
                        setState('pedding');
                    }

                    removeFile(file)
                    updateTotalProgress()
                }

                uploader.on('all', function(type) {
                    var stats

                    switch(type) {
                        case 'uploadFinished':
                            setState('confirm')

                            break
                        case 'startUpload':
                            setState('uploading')

                            break
                        case 'stopUpload':
                            setState('paused')

                            break
                    }
                })

                uploader.onError = function(code) {
                    if (code === 'Q_EXCEED_NUM_LIMIT') {
                        BJUI.alertmsg('info', '只允许上传'+ options.fileNumLimit + upunit +'！')
                    } else if (code === 'Q_TYPE_DENIED') {
                        BJUI.alertmsg('info', '不支持的文件类型！')
                    } else if (code === 'F_EXCEED_SIZE') {
                        BJUI.alertmsg('info', '文件太大了！')
                    } else if (code === 'F_DUPLICATE') {
                        BJUI.alertmsg('info', '已添加过该文件！')
                    } else {
                        BJUI.alertmsg('info', code)
                    }
                }

                $upload.on('click', function() {
                    if ($(this).hasClass('disabled')) {
                        return false
                    }

                    if (state === 'ready') {
                        uploader.upload()
                    } else if (state === 'paused') {
                        uploader.upload()
                    } else if (state === 'uploading') {
                        uploader.stop()
                    }
                })

                $info.on('click', '.retry', function() {
                    uploader.retry()
                })

                $info.on('click', '.ignore', function() {
                    alert('todo')
                })

                $upload.addClass('state-' + state)
                updateTotalProgress()

                $element.data('webuploader', uploader).data('webuploader.wrap', $wrap)
            }
        }
    }

    $box.find('input[data-toggle="webuploader"]').each(function(i) {
        initWebUploader($(this), i)

        $(this).on('reload.webuploader', function() {
            initWebUploader($(this), i)
        })
    })
}
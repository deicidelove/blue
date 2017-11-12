
	 UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
     UE.Editor.prototype.getActionUrl = function(action) {
    	 if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
             return 'http://localhost:8082/upPic';
         } else if (action == 'uploadvideo') {
             return 'http://a.b.com/video.php';
         } else {
             return this._bkGetActionUrl.call(this, action);
         }
         
     }


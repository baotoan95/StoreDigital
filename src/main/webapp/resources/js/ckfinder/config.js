/*
Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
For licensing, see license.txt or http://cksource.com/ckfinder/license
*/

CKFinder.customConfig = function( config )
{
	var duong_dan='/StoreDigital/resources/js/';
	config.filebrowserBrowseUrl = duong_dan + '/ckfinder/ckfinder.html',
	config.filebrowserImageBrowseUrl = duong_dan + '/ckfinder/ckfinder.html?type=Images',
	config.filebrowserFlashBrowseUrl = duong_dan + '/ckfinder/ckfinder.html?type=Flash',
	config.filebrowserUploadUrl = duong_dan + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
	config.filebrowserImageUploadUrl = duong_dan + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
	config.filebrowserFlashUploadUrl = duong_dan + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
	config.filebrowserWindowWidth = duong_dan + '1000',
	config.filebrowserWindowHeight = duong_dan + '700'
};

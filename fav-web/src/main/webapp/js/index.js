$(function() {
		$("#listFavorites").panel({
			fit : true,
			title : " ",
			border : false,
		});
		
		$("#addFav").dialog({
			height:260, 
			width:380,
			title:'',
			border:false,
			modal:true
		});
		
		$("#addFav").dialog("close", true);
		
		$("#labels_table td").hover(selecrLabel);
		
		
		//以异步的方式取到所有书签的信息
		$.post("/fav-web/tag/findAll", function(data){
			//alert(JSON.stringify(data));  //JSON.stringify() ,把json对象转换成json字符串
			var tagTagetStr = "";
			
			for (var i = 0; i < data.length; i++) {
				tagTagetStr+='<tr><td><a href="#?type=' + data[i].tname + '">'+ data[i].tname + '</a></td></tr>';
			}
			
			$("#cloudPic").before(tagTagetStr);
			
			$("#labels_table td").hover(selecrLabel);
		}, "json");
	});
	
	function add(){
		$("#addFav").dialog("open", true);
	}
	
	function addWinClose(){
		$("#addFav").dialog("close", true);
		return false;
	}
	
	function  selecrLabel(){
		$("#labels_table td").removeClass("selected_label");
		$(this).addClass("selected_label");
	}
	
	function addFavorite(){
		var jsonStr = toJsonStr($("#addFavForm").serialize());
		$.post("favorite/add", {"param":jsonStr}, function(data){
			alert(data);
		}, "json");
		
		return false;
	}
	
	function toJsonStr(str){
		return str = "{\"" + str.replace(/=/g, "\":\"").replace(/&/g, "\",\"") + "\"}";
	}
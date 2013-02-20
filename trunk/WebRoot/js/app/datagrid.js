/**
 * 此方法传入以下参数
 * id: 创建datagrid的table的id
 * type: 创建的datagrid的类型，包含如下几种:
 *       Basisc : 基础类型，所有参数自己指定，传入options对象
 *       Variable : 可变类型，列可变，需指定action、data、columns、update(双击某列的时候的更新方法)
 *       Pagination : 带分页，参数指定和Variable相同
 */

var DatagridFactory = {
	createDatagrid : function(option) {
		var autoWidth = document.body.clientWidth*0.83;
		if(option.type == 'Basic') {
			$(option.id).datagrid(option.options).datagrid("columnMoving");
		} else if(option.type == 'Variable'){
			$(option.id).datagrid({
				url : option.action,
				columns: option.columns,
				loadMsg: "数据加载中，请您耐心等待...",
				striped : true,
				singleSelect : true,
				width : autoWidth,
				queryParams : option.data,
				onHeaderContextMenu: function(e, field){ //这个事件是在datagrid中点击右键的时候调用的方法
					e.preventDefault();
					if (!$(option.id + "_menu").length){//如果没有这个菜单就创建
						createColumnMenu();
					}
					$(option.id + "_menu").menu('show', {//如果有这个菜单就显示在鼠标点击的位置
						left:e.pageX,
						top:e.pageY
					});
				},//end onHeaderContextMenu
				onDblClickRow : option.update
			}).datagrid("columnMoving");
			function createColumnMenu(){
				var aa = option.id.substring(1,option.id.length) + "_menu";
				//alert(aa);
				var tmenu = $('<div id="'+aa+'" style="width:100px;"></div>').appendTo('body');//创建菜单的div
				var fields = $(option.id).datagrid('getColumnFields');//获取菜单的所有field
				var name = {};//用于菜单显示文字
				var show = {};//用于判断菜单是否隐藏
				for(var i=0; i<fields.length; i++){
					var o = $(option.id).datagrid('getColumnOption',fields[i]);//获取到列的属性
					name[i] = o.title;//获取到title用于显示
					show[i] = !o.hidden;//获取到hidden判断是否隐藏
				}
				for(var i=0; i<fields.length; i++){
					if(show[i]) {
						$('<div iconCls="icon-ok" name="'+fields[i]+'"/>').html(name[i]).appendTo(tmenu);//将菜单项插入菜单
					} else {
						$('<div iconCls="icon-empty" name="'+fields[i]+'"/>').html(name[i]).appendTo(tmenu);//将菜单项插入菜单，但是不显示
					}
				}
				tmenu.menu({//将menu渲染
					onClick: function(item){//绑定点击事件
						if (item.iconCls=='icon-ok'){//如果目前是显示
							$(option.id).datagrid('hideColumn', item.name);//调用datagrid的方法，隐藏这列
							tmenu.menu('setIcon', {//设置图标
								target: item.target,
								iconCls: 'icon-empty'//换为空的图标
							});
						} else {//如果是不显示此列
							$(option.id).datagrid('showColumn', item.name);//显示这一列
							tmenu.menu('setIcon', {//设置回原来的图标
								target: item.target,
								iconCls: 'icon-ok'
							});
						}
					}//end onClick
				});//end menu
			};//end createColumnMenu
		} else if(option.type == "Pagination") {
			$(option.id).datagrid({
				url : option.action,
				columns: option.columns,
				loadMsg: "数据加载中，请您耐心等待...",
				striped : true,
				singleSelect : true,
				rownumbers : function(){
					if(option.rownumbers)
						return true;
					else
						return false;
				},
				width : autoWidth,
				queryParams : option.data,
				pagination : true,
				pageSize : 15,
				pageList : [15,20,25,30,35],
				onHeaderContextMenu: function(e, field){ //这个事件是在datagrid中点击右键的时候调用的方法
					e.preventDefault();
					if (!$(option.id + "_menu").length){//如果没有这个菜单就创建
						createColumnMenu();
					}
					$(option.id + "_menu").menu('show', {//如果有这个菜单就显示在鼠标点击的位置
						left:e.pageX,
						top:e.pageY
					});
				},//end onHeaderContextMenu
				onDblClickRow : option.update
			}).datagrid("columnMoving");//end datagrid
			function createColumnMenu(){
				var aa = option.id.substring(1,option.id.length) + "_menu";
				//alert(aa);
				var tmenu = $('<div id="'+aa+'" style="width:100px;"></div>').appendTo('body');//创建菜单的div
				var fields = $(option.id).datagrid('getColumnFields');//获取菜单的所有field
				var name = {};//用于菜单显示文字
				var show = {};//用于判断菜单是否隐藏
				for(var i=0; i<fields.length; i++){
					var o = $(option.id).datagrid('getColumnOption',fields[i]);//获取到列的属性
					name[i] = o.title;//获取到title用于显示
					show[i] = !o.hidden;//获取到hidden判断是否隐藏
				}
				for(var i=0; i<fields.length; i++){
					if(show[i]) {
						$('<div iconCls="icon-ok" name="'+fields[i]+'"/>').html(name[i]).appendTo(tmenu);//将菜单项插入菜单
					} else {
						$('<div iconCls="icon-empty" name="'+fields[i]+'"/>').html(name[i]).appendTo(tmenu);//将菜单项插入菜单，但是不显示
					}
				}
				tmenu.menu({//将menu渲染
					onClick: function(item){//绑定点击事件
						if (item.iconCls=='icon-ok'){//如果目前是显示
							$(option.id).datagrid('hideColumn', item.name);//调用datagrid的方法，隐藏这列
							tmenu.menu('setIcon', {//设置图标
								target: item.target,
								iconCls: 'icon-empty'//换为空的图标
							});
						} else {//如果是不显示此列
							$(option.id).datagrid('showColumn', item.name);//显示这一列
							tmenu.menu('setIcon', {//设置回原来的图标
								target: item.target,
								iconCls: 'icon-ok'
							});
						}
					}//end onClick
				});//end menu
			};//end createColumnMenu
		}//end else if
	}//end createDatagrid
	
};

/**
 * 为datagrid扩展可移动表头
 */
$.extend($.fn.datagrid.methods,{
	columnMoving: function(jq){
		return jq.each(function(){
			var target = this;
			var cells = $(this).datagrid('getPanel').find('div.datagrid-header td[field]');
			cells.draggable({
				revert:true,
				cursor:'pointer',
				edge:5,
				proxy:function(source){
					var p = $('<div class="tree-node-proxy tree-dnd-no" style="position:absolute;border:1px solid #ff0000"/>').appendTo('body');
					p.html($(source).text());
					p.hide();
					return p;
				},
				onBeforeDrag:function(e){
					e.data.startLeft = $(this).offset().left;
					e.data.startTop = $(this).offset().top;
				},
				onStartDrag: function(){
					$(this).draggable('proxy').css({
						left:-10000,
						top:-10000
					});
				},
				onDrag:function(e){
					$(this).draggable('proxy').show().css({
						left:e.pageX+15,
						top:e.pageY+15
					});
					return false;
				}
			}).droppable({
				accept:'td[field]',
				onDragOver:function(e,source){
					$(source).draggable('proxy').removeClass('tree-dnd-no').addClass('tree-dnd-yes');
					$(this).css('border-left','1px solid #ff0000');
				},
				onDragLeave:function(e,source){
					$(source).draggable('proxy').removeClass('tree-dnd-yes').addClass('tree-dnd-no');
					$(this).css('border-left',0);
				},
				onDrop:function(e,source){
					$(this).css('border-left',0);
					var fromField = $(source).attr('field');
					var toField = $(this).attr('field');
					setTimeout(function(){
						moveField(fromField,toField);
						$(target).datagrid();
						$(target).datagrid('columnMoving');
					},0);
				}
			});
			
			// move field to another location
			function moveField(from,to){
				var columns = $(target).datagrid('options').columns;
				var cc = columns[0];
				var c = _remove(from);
				if (c){
					_insert(to,c);
				}
				
				function _remove(field){
					for(var i=0; i<cc.length; i++){
						if (cc[i].field == field){
							var c = cc[i];
							cc.splice(i,1);
							return c;
						}
					}
					return null;
				}
				function _insert(field,c){
					var newcc = [];
					for(var i=0; i<cc.length; i++){
						if (cc[i].field == field){
							newcc.push(c);
						}
						newcc.push(cc[i]);
					}
					columns[0] = newcc;
				}
			}
		});
	}
});
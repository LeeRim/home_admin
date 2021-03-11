
(function ($) {
    "use strict";
    
    let item_table = $("#item-table").DataTable({
    	fixedHeader: true,
    	lengthChange: false,
    	pageLength: 30,
    	columns: [
    		{ "data": "usageName" },
    		{ "data": "division" },
    		{ "data": "itemName" },
    		{ "data": "areaName" },
    		{ "data": "placeName" },
    		{ "data": "spot" },
    		{ "data": "unit" },
    		{ "data": "count" },
    		{ "data": "action" }
    		]
    });
    
    drawTableData(item_table,null);
    
    $.ajax({
		type: 'GET',
		url: '/data/usage'
	}).done(function(data, textStatus, xhr){
		let usageList=[];
		let usageMap={};
		$.each(data,function(index,row){
			usageMap={};
			usageMap.key=row.usageKey;
			usageMap.label=row.usageName;
			usageList.push(usageMap);
		});
		addCheckHtml("usageKey","사용처 선택",usageList);
	    
	}).fail(function(xhr, textStatus, error){
//		console.log(xhr.responseText);
	}).always(function(){
		let divList=[{key:"somo",label:"소모품"},{key:"bi",label:"비품"}];
		addCheckHtml("division","구분 선택",divList);
		addCheckEvent("#item-table_wrapper");
	});
    
    
    // check object
    function Check() {
    	this.div_html="";
    	this.list_html="";
    	this.create = function(id,title){
    		// #check-div is check template
    		let div = $("#check-div").clone(true);
    		div.find("div.check-list").attr("id",id);
    		div.find("p").text(title);
    		div.find("div.checkbox").remove();
    		this.div_html = div.html();
    	}
    	this.setList = function(list){
    		let input_box = $("#check-div .checkbox").clone(true);
    		let input_html='';
    		let row_html='';
    		$.each(list,function(index, row){
    			row_html=input_box.clone(true);
    			row_html.find("input").attr("id",row.key);
    			row_html.find("label").append(row.label);
    			row_html.find("label").attr("for",row.key);
    			input_html+=row_html.prop('outerHTML');
    		});
    		this.list_html=$(this.div_html).append(input_html).prop('outerHTML');
    	}
    	this.add = function(div_id){
    		$(div_id).children().eq(0).children().eq(0).append($(this.list_html));
    	}
    }
    
    /*function Check() {
    	this.div_html="";
    	this.list_html="";
    	this.create = function(title){
    		this.div_html = '<div class="check-list"> \
    			<p>'+title+'</p> \
    		</div>';
    	}
    	this.setList = function(list){
    		let input_box = '<label> \
    	    	<input type="checkbox" class="flat" checked="checked" /> \
    			</label>';
    		let input_html='';
    		let row_html='';
    		$.each(list,function(index, row){
    			row_html=$(input_box).append(row);
    			input_html+=row_html.prop('outerHTML');
    		});
    		this.list_html=$(this.div_html).append(input_html).prop('outerHTML');
    	}
    	this.add = function(div_id){
    		$(div_id).children().eq(0).children().eq(0).append(this.list_html);
    	}
    }*/
    
    function drawTableData(table,param){
    	console.log(param);
    	let url='/data/item';
    	if(param!=null){
    		url+="?"+param;
    	}
    	
        $.ajax({
        	type: 'GET',
        	url: url
        }).done(function(data, textStatus, xhr){
        	let datas=new Array();
        	let d={};
        	$.each(data,function(index,row){
        		
        		d.usageName=row.usage.usageName;
        		d.division=(row.division=="somo")? "소모품":"비품";
        		d.itemName=row.itemName;
        		d.areaName=row.place.areaName;
        		d.placeName=row.place.placeName;
        		d.spot=row.spot;
        		d.unit=row.unit;
        		d.count=row.count;
        		d.action="";
        		datas.push(d);
        		d={};
        	});
        	
        	table.clear();
        	table.rows.add(datas).draw();
        }).fail(function(xhr, textStatus, error){
//    		console.log(xhr.responseText);
        });
    }

    function addCheckHtml(id,title,list){
    	let check = new Check();
    	check.create(id,title);
    	check.setList(list);
    	check.add("#item-table_wrapper");
    }
    
    function addCheckEvent(wrapper_id){
    	$(wrapper_id+" input[type='checkbox']").click(function(){
    		let $this_input = $(this);
    		console.log($this_input);
    		let key="";
    		let params={};
    		$(wrapper_id).find("input[type='checkbox']").each(function(index,row){
    			key = $(row).parents("div.check-list").attr("id");
    			if(params[key]==null){
    				params[key]=[];
    			}
    			if($(row).prop("checked")){    				
    				params[key].push($(row).attr("id"));
    			}
    		});
    		let paramStr="";
    		for(key in params){
    			paramStr+=key+"=";
    			paramStr+=params[key].join(",");
    			paramStr+="&";
    		}
    		drawTableData(item_table,paramStr);
    	});
    }
    
    
})(jQuery);
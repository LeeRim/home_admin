
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
    
    let datas=new Array();
    $.ajax({
    	type: 'GET',
    	url: '/data/item'
    }).done(function(data, textStatus, xhr){
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
    	
    	item_table.rows.add(datas).draw();

    }).fail(function(xhr, textStatus, error){
//		console.log(xhr.responseText);
    });
    
    $("div.checkbox").off("click").click(function(){
		let $this_input = $(this).find("input");
//		let check_bool = $this.find("input").attr("checked");
		if ($this_input.is(":checked")){
			$this_input.prop("checked",false);
		}else{
			$this_input.prop("checked",true);
		}
		console.log($this_input);
	});
    
    
    // check object
    function Check() {
    	this.div_html="";
    	this.list_html="";
    	this.create = function(title){
    		// #check-div is check template
    		let div = $("#check-div").clone(true);
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

    function addCheckHtml(title,list){
    	let check = new Check();
    	check.create(title);
    	check.setList(list);
    	check.add("#item-table_wrapper");
    }
    
    function addCheckEvent(){
    	$("div.checkbox").off("click").click(function(){
    		let $this_input = $(this).find("input");
//    		let check_bool = $this.find("input").attr("checked");
    		if ($this_input.is(":checked")){
    			$this_input.prop("checked",false);
    		}else{
    			$this_input.prop("checked",true);
    		}
    		console.log($this_input);
    	});
    }
    
    
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
		addCheckHtml("사용처 선택",usageList);
	    
	}).fail(function(xhr, textStatus, error){
//		console.log(xhr.responseText);
	}).always(function(){
		let divList=[{key:"div_1",label:"소모품"},{key:"div_2",label:"비품"}];
		addCheckHtml("구분 선택",divList);
//		addCheckEvent();
	});
    
    
    
})(jQuery);
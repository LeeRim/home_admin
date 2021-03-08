
(function ($) {
    "use strict";
    
    $("#item-table").DataTable({
    	fixedHeader: true,
    	lengthChange: false,
    	pageLength: 30
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
    
    
    $.ajax({
		type: 'GET',
		url: '/item/data/usage'
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
	});
    
    $.ajax({
		type: 'GET',
		url: '/item/data/item'
	}).done(function(data, textStatus, xhr){
		let tr=null;
		$.each(data,function(index,row){
			tr = $("#item-table tbody").append("<tr />");
			console.log(row);
			tr.append("<td>"+row.usage.usageName+"</td>");
			tr.append("<td>"+row.division+"</td>");
			tr.append("<td>"+row.itemName+"</td>");
			tr.append("<td>"+row.placeFK+"</td>");
			tr.append("<td>"+row.placeFK+"</td>");
			tr.append("<td>"+row.spot+"</td>");
			tr.append("<td>"+row.unit+"</td>");
			tr.append("<td>"+row.count+"</td>");
		});
	    
	}).fail(function(xhr, textStatus, error){
//		console.log(xhr.responseText);
	});
    
    
})(jQuery);
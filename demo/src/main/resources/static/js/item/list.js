
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
    }
    
    let usage_check = new Check();
    usage_check.create("사용처 선택");
    usage_check.setList(["1","2","3"]);
    usage_check.add("#item-table_wrapper");
    
    let division_check = new Check();
    division_check.create("구분 선택");
    division_check.setList(["소모품","비품"]);
    division_check.add("#item-table_wrapper");
    
})(jQuery);
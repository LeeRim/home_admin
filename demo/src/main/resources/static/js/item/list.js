
(function($) {
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
			{ "data": "count" }
		],
		createdRow: function(row, data, dataIndex) {
			$(row).data("item", data);
		}
	});

	// draw all data (param:null)
	drawTableData(item_table, null);

	$('#item-table tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		}
		else {
			item_table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	$("#item-table_wrapper").children().eq(0).children().eq(1).append($("#button-div"));

	$("#btn-useone").click(function() {
		let tr = $("#item-table tr.selected");
		if (tr.length != 0) {
			let item = tr.data("item");
			let reItem = null;
			let bool = confirm("[ " + item.itemName + " ]을/를 1개 사용하시겠습니까?");
			if (bool) {
				selectItem(item.itemKey, function(data) {
					data.count -= 1;
					updateItem(data, function() {
						alert("[ " + item.itemName + " ]을/를 1개 사용하여\n현재 남은 수량은 \'" + data.count + "\' 입니다");
						drawTableData(item_table, null);
					});
				});
			}
		} else {
			alert("제품을 선택해주세요");
		}
	});

	$("#btn-create").click(function() {
		let item = $("#create-form").serializeObject();
		let bool = confirm("[ " + item.itemName + " ]을/를 생성하시겠습니까?");
		if (bool) {
			createItem(item, function() {
				alert("[ " + item.itemName + " ]을/를 생성하였습니다");
				drawTableData(item_table, null);
				$("button[data-dismiss='modal']").trigger("click");
			});
		}
	});

	$("#btn-show-update").click(function() {
		let tr = $("#item-table tr.selected");
		if (tr.length != 0) {
			$("#updateModal").modal("show");
			let item = tr.data("item");
			selectItem(item.itemKey, function(data) {
				$.each(data,function(key, val){
					if(typeof(val)=="object"){
						$.each(val,function(k,v){
							$("#update-form [data-key='"+k+"']").val(v);
						});
					}else{
						if($("#update-form [name='"+key+"']").attr("type")=="radio"){
							$("#update-form [value='"+val+"']").prop("checked","true");
						}else{							
							$("#update-form [name='"+key+"']").val(val);
						}
					}
				});
				$("#update-form .select-area").trigger("change");
			});
		} else {
			alert("제품을 선택해주세요");
		}
	});
	
	$("#btn-update").click(function() {
		let item = $("#update-form").serializeObject();
		let bool = confirm("[ " + item.itemName + " ]을/를 수정하시겠습니까?");
		if (bool) {
			updateItem(item, function() {
				alert("[ " + item.itemName + " ]을/를 수정하였습니다");
				drawTableData(item_table, null);
				$("button[data-dismiss='modal']").trigger("click");
			});
		}
	});

	// draw 'usage' check box & select option
	$("#item-table_wrapper").children().eq(0).children().eq(0).append("<form id='check-form'></form>")
	$.ajax({
		type: 'GET',
		url: '/data/usage'
	}).done(function(data, textStatus, xhr) {
		let usageList = [];
		let usageMap = {};
		let option = "<option></option>";
		let op = null;
		$.each(data, function(index, row) {
			usageMap = {};
			usageMap.key = row.usageKey;
			usageMap.label = row.usageName;
			usageList.push(usageMap);

			op = $(option).text(row.usageName);
			$(op).val(row.usageKey);
			$(".select-usage").append(op);
			op = null;
		});

		let usageCheck = new Check();
		usageCheck.init("사용처 선택", "usageKey", usageList);

	}).fail(function(xhr, textStatus, error) {
		//		console.log(xhr.responseText);
	}).always(function() {
		let divList = [{ key: "somo", label: "소모품" }, { key: "bi", label: "비품" }];
		let divCheck = new Check();
		divCheck.init("구분 선택", "division", divList);
		$("#item-table_wrapper input[type='checkbox']").click(function() {
			drawTableData(item_table, $("#check-form").serialize());
		});
	});

	// draw 'place' select option
	$.ajax({
		type: 'GET',
		url: '/data/place'
	}).done(function(data, textStatus, xhr) {
		let option = "<option></option>";
		let op = null;
		let areaList = [];
		$.each(data, function(index, row) {
			if (areaList.indexOf(row.areaName) == -1) {
				areaList.push(row.areaName);
				op = $(option).text(row.areaName);
				$(".select-area").append(op);
				op = null;
			}

			op = $(option).text(row.placeName);
			$(op).val(row.placeKey);
			$(op).data("area", row.areaName);
			$(op).attr("hidden", true);
			$(".select-place").append(op);
			op = null;
		});

		$(".select-area").change(function() {
			let area = $(this).val();
			let places = $(this).parents(".input-div").find(".select-place option");
			$.each(places, function(index, row) {
				if ($(row).data("area") == area) {
					$(row).attr("hidden", false);
				} else {
					$(row).attr("hidden", true);
				}
			});
		});

	}).fail(function(xhr, textStatus, error) {
		//		console.log(xhr.responseText);
	});

	function drawTableData(table, param) {
		console.log(param);
		let url = '/data/item';
		if (param != null) {
			url += "?" + param;
		}

		$.ajax({
			type: 'GET',
			url: url
		}).done(function(data, textStatus, xhr) {
			let datas = new Array();
			let d = {};
			$.each(data, function(index, row) {

				d.itemKey = row.itemKey;
				d.usageName = row.usage.usageName;
				d.division = (row.division == "somo") ? "소모품" : "비품";
				d.itemName = row.itemName;
				d.areaName = row.place.areaName;
				d.placeName = row.place.placeName;
				d.spot = row.spot;
				d.unit = row.unit;
				d.count = row.count;
				datas.push(d);
				d = {};
			});

			table.clear();
			table.rows.add(datas).draw();

		}).fail(function(xhr, textStatus, error) {
			//    		console.log(xhr.responseText);
		});
	}

	// check object
	function Check() {
		this.div_html = "";
		this.list_html = "";
		this.init = function(title, name, list) {
			this.create(title);
			this.setList(name, list);
			this.add("#item-table_wrapper");
		};
		this.create = function(title) {
			// #check-div is check template
			let div = $("#check-div").clone(true);
			div.find("p").text(title);
			div.find("div.checkbox").remove();
			this.div_html = div.html();
		};
		this.setList = function(name, list) {
			let input_box = $("#check-div .checkbox").clone(true);
			let input_html = '';
			let row_html = '';
			$.each(list, function(index, row) {
				row_html = input_box.clone(true);
				row_html.find("input").val(row.key);
				row_html.find("input").attr("id", row.key);
				row_html.find("input").attr("name", name);
				row_html.find("label").append(row.label);
				row_html.find("label").attr("for", row.key);
				input_html += row_html.prop('outerHTML');
			});
			this.list_html = $(this.div_html).append(input_html).prop('outerHTML');
		};
		this.add = function(div_id) {
			$(div_id).children().eq(0).children().eq(0).children().eq(0).append($(this.list_html));
		};
	}

	function addCheckEvent(wrapper_id) {
		$(wrapper_id + " input[type='checkbox']").click(function() {
			drawTableData(item_table, $("#check-form").serialize());
		});
	}

	function selectItem(key, callback = function() { }) {
		let url = '/data/item';
		let selectData = null;
		$.ajax({
			type: 'GET',
			url: url + "/" + key
		}).done(function(data, textStatus, xhr) {
			callback(data);
		}).fail(function(xhr, textStatus, error) {
			//    		console.log(xhr.responseText);
		});
	}

	function createItem(data, callback = function() { }) {
		let url = '/data/item';
		let result = 0;
		$.ajax({
			type: 'POST',
			headers: {
				"Content-Type": "application/json"
			},
			data: JSON.stringify(data),
			url: url
		}).done(function(data, textStatus, xhr) {
			result = xhr.status;
			callback();
		}).fail(function(xhr, textStatus, error) {
			//    		console.log(xhr.responseText);
		});
	}

	function updateItem(data, callback = function() { }) {
		let url = '/data/item';
		let result = 0;
		$.ajax({
			type: 'POST',
			headers: {
				"Content-Type": "application/json"
			},
			data: JSON.stringify(data),
			url: url + "/" + data.itemKey
		}).done(function(data, textStatus, xhr) {
			result = xhr.status;
			callback();
		}).fail(function(xhr, textStatus, error) {
			//    		console.log(xhr.responseText);
		});
	}

	$.fn.serializeObject = function() {
		var obj = null;
		try {
			if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
				var arr = this.serializeArray();
				if (arr) {
					obj = {};
					$.each(arr, function() {
						if (this.name.indexOf(".") == -1) {
							obj[this.name] = this.value;
						} else {
							let names = this.name.split(".");
							obj[names[0]] = {};
							obj[names[0]][names[1]] = this.value;
						}
					});
				}
			}
		} catch (e) {
			alert(e.message);
		} finally {

		} return obj;
	};

})(jQuery);

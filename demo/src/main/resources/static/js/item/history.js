
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
			{ "data": "count" },
			{ "data": "history" }
		],
		createdRow: function(row, data, dataIndex) {
			$(row).data("item", data);
		}
	});

	// draw all data (param:null)
	drawTableData(item_table, null);

	function drawTableData(table, param) {
		console.log(param);
		let url = '/data/history';
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
				d.history = "<a href='/item/history?itemKey=" + row.itemKey + "'>이력보기</a>"
				datas.push(d);
				d = {};
			});

			table.clear();
			table.rows.add(datas).draw();

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

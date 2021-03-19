
(function($) {
	"use strict";

	$.crud = {
		select: function(url, callback = function() { }) {
			try {
				$.ajax({
					type: 'GET',
					url: url
				}).done(function(data, textStatus, xhr) {
					callback(data);
				}).fail(function(xhr, textStatus, error) {
					console.log(xhr.responseText);
				});
			} catch (e) {
				alert(e.message);
			} finally {

			}
		},
		create: function(url, data, callback = function() { }) {
			try {
				$.ajax({
					type: 'POST',
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(data),
					url: url
				}).done(function(data, textStatus, xhr) {
					callback();
				}).fail(function(xhr, textStatus, error) {
					console.log(xhr.responseText);
				});
			} catch (e) {
				alert(e.message);
			} finally {

			}
		},
		update: function(url, data, callback = function() { }) {
			try {
				$.ajax({
					type: 'POST',
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(data),
					url: url
				}).done(function(data, textStatus, xhr) {
					callback();
				}).fail(function(xhr, textStatus, error) {
					console.log(xhr.responseText);
				});
			} catch (e) {
				alert(e.message);
			} finally {

			}
		}

	}

})(jQuery);

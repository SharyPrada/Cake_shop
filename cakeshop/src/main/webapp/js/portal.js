function getCategories(category_id) {
	$.getJSON("../ws/portal/categories", function(result) {
		data = result.data;
		$("#div_categories").empty();
		for (var row = 0; row < data.length; row = row + 1) {
			var id = data[row].id;
			var name = data[row].name;
			var icon = data[row].icon;
			var item_class = "list-group-item";
			if (id == id_category) {
				item_class = "list-group-item active";
			}
			$("#div_categories").append(
					"<a href='javascript:getProducts(" + id
							+ ");' id='category_" + id + "' class='"
							+ item_class + "'>" + name + "</a>");
		}
	});
}
function getProducts(id_category) {
	$('.list-group-item').removeClass('active').addClass('');
	$("#category_" + id_category).addClass('active');
	$.getJSON("../cakeshop/portal/product/" + id_category, function(result) {
		data = result.data;
		$("#div_products").empty();
		for (var row = 0; row < data.length; row = row + 1) {
			var id = data[row].id;
			var name = data[row].name;
			var icon = data[row].icon;
			var price = data[row].price;
			var description = data[row].description;
			var posted = data[row].posted;
			var url = "../item/index.jsp?id=" + id;
			var item = '<div class="col-lg-4 col-md-6 mb-4">';
			item += '<div class="card h-100">';
			item += '<a id="link_title" href="' + url + '">';
			item += '<img class="card-img-top" src="../fotos/' + icon
					+ '" alt="">';
			item += '</a>';
			item += '<div class="card-body">';
			item += '<h4 class="card-title">';
			item += '<a href="' + url + '">' + name + '</a>';
			item += '</h4>';
			item += '<h5>$' + price+ '</h5>';
			item += '<p class="card-text">' + description + '</p>';
			
			item += '<a href="javascript:addToCart(' + id + ');" class="btn btn-info" role="button">Buy</a>';
			
			item += '</div>';
			item += '<div class="card-footer">';
			item += '<small class="text-muted">';
			item += '&#9733; &#9733; &#9733; &#9733;&#9734;';
			item += '</small>';
			item += '</div>';
			item += '</div>';
			item += '</div>';
			$("#div_products").append(item);
		}
	});
}

function updateItemsCount(){
	$.getJSON("../ws/cart/items", function(json) {
		var items = json.items;
		$("#shopping_cart").text(items);
	});
}

function addToCart(product_id){
	$.getJSON("../ws/cart/add/" + product_id, function(json) {
		var items = json.items;
		$("#shopping_cart").text(items);
	});
}
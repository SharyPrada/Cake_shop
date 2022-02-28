<!-- Modal -->
<div class="modal fade" id="productModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<form id="productForm" method="POST" action="../api/product/">
			<input type="hidden" id="id" name="id" value="" />
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel"> Adiciona un producto</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="id_category">Id_category</label> <input type="text"
							class="form-control" id="id_category" name="id_category" placeholder="">
					</div>
					<div class="form-group">
						<label for="name">Name</label> <input type="text"
							class="form-control" id="name" name="name" placeholder="">
					</div>
					<div class="form-group">
						<label for="price">Price</label> <input type="text"
							class="form-control" id="price" name="price" placeholder="">
					</div>
					<div class="form-group">
						<label for="photo">Photo</label> <input type="text"
							class="form-control" id="photo" name="photo" placeholder="">
					</div>
					<div class="form-group">
						<label for="description">Description</label> <input type="text"
							class="form-control" id="description" name="description" placeholder="">
					</div>
					<div class="form-group">
						<label for="posted">Posted</label> <input type="text"
							class="form-control" id="posted" name="posted" placeholder="">
					</div>
					
					
					
				</div>
				<!-- end modal-body -->
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" id="sendJSON">Save
						changes</button>
				</div>
			</div>
			<!-- end modal-content -->
		</form>
	</div>
</div>
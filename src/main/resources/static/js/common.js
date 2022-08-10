$(function() {
	
	// sign out
	$('#signout').click(function() {
		$('#logoutForm').submit()
	})
	
	// edit category
	$('.editCategory').click(function(event) {
		
		let catId = event.currentTarget.id
		let catName  = $(event.currentTarget).prev().html()
		let action = $('#editForm').attr("action")
		
		$('#editForm').attr('action', `${action}/${catId}`)
		$('#editName').val(catName)
		
		$('#editDialog').modal()
	})
	
	$('#saveBtn').click(function() {
		$('#editForm').submit()
	})
	
	$('#addNew').click(function() {
		$('#editDialog').modal()
	})
	
	$('.edit-item').click(function() {
		let action = $(this).attr("href")
		
		$.get(action, function(data) {
			
			$('#id').val(data.id)
			$('#name').val(data.name)
			$('#price').val(data.price)
			$('#category').val(data.category.id)
			
			$('#editDialog').modal()
		})
		
		return false
	})
	
	$('.changepass').click(function() {
		
		let url = $(this).attr('href')
		
		$('#editForm').attr('action', url)
		
		$('.modal').modal()
		
		return false
	})
	
	$('.burn-user').click(function() {
		
		let url = $(this).attr('href')
		
		$('#burnForm')
			.attr('action', url)
			.submit()
			
		return false
	})
	
	$('.addToCart').click(function() {
		let url = $(this).attr('href')
		
		$.get(url, function(data) {
			console.log(data);
			
			$('.badge').html(data)
		})
		
		return false
	})
	
	let changeStatus = function(status) {
	
		let form = $('#invForm')
		$(form).attr('action', `${$(form).attr('action')}/${status}`)
		$(form).submit()
	}
	
	$('#cancel').click(function(){
		changeStatus('Cancel')
	})
	
	$('#finish').click(function(){
		changeStatus('Finish')
	})
	
	$('#print').click(function(){
		$('#printForm').submit()
	})
	
	$('#printList').click(function() {
		let action = $('#searchForm').attr('action').replace('home', 'invoice/print')
		$('#searchForm').attr('action', action).submit()
	})
	
})



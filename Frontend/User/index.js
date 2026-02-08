fetch("http://localhost:8080/api/products")
   .then(res => res.json())
   .then(products => {
    const container = document.getElementById("product-list");
    container.innerHTML = "";
    
    if (products.length === 0) {
        container.innerHTML = '<div class="col-12 text-center"><p>No products available yet.</p></div>';
        return;
    }
    
    products.forEach(p => {
        container.innerHTML += `
        <div class="col-md-4 mb-4">
          <div class="card h-100">
            <img src="${p.img}" class="card-img-top" alt="${p.name}" style="height: 250px; object-fit: cover;">
            <div class="card-body">
              <h5 class="card-title">${p.name}</h5>
              <p class="card-text"><strong>Category:</strong> ${p.category}</p>
              <p class="card-text"><strong>Price:</strong> ₹${p.price}</p>
              <button class="btn btn-success" onclick="openOrderModal(${p.id}, '${p.name}', ${p.price})">Order Now</button>
            </div>
          </div>
        </div>
      `;
    });
  })
  .catch(err => {
    console.error("Error loading products:", err);
    document.getElementById("product-list").innerHTML = 
      '<div class="col-12 text-center"><p class="text-danger">Failed to load products. Make sure the server is running.</p></div>';
  });

function openOrderModal(productId, productName, price) {
   
    const modalHTML = `
    <div class="modal fade" id="orderModal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Place Order - ${productName}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <form id="orderForm">
              <input type="hidden" id="productId" value="${productId}">
              <input type="hidden" id="productName" value="${productName}">
              <input type="hidden" id="productPrice" value="${price}">
              
              <div class="mb-3">
                <label for="customerName" class="form-label">Full Name *</label>
                <input type="text" class="form-control" id="customerName" required>
              </div>
              
              <div class="mb-3">
                <label for="customerEmail" class="form-label">Email *</label>
                <input type="email" class="form-control" id="customerEmail" required>
              </div>
              
              <div class="mb-3">
                <label for="customerPhoneNo" class="form-label">Phone Number *</label>
                <input type="tel" class="form-control" id="customerPhoneNo" pattern="[0-9]{10}" required>
              </div>
              
              <div class="mb-3">
                <label for="address" class="form-label">Delivery Address *</label>
                <textarea class="form-control" id="address" rows="3" required></textarea>
              </div>
              
              <div class="mb-3">
                <label for="quantity" class="form-label">Quantity *</label>
                <input type="number" class="form-control" id="quantity" value="1" min="1" max="10" required>
              </div>
              
              <div class="mb-3">
                <h5>Total Price: ₹<span id="totalPrice">${price}</span></h5>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-primary" onclick="placeOrder()">Place Order</button>
          </div>
        </div>
      </div>
    </div>
    `;
    
    const existingModal = document.getElementById('orderModal');
    if (existingModal) {
        existingModal.remove();
    }
    
    document.body.insertAdjacentHTML('beforeend', modalHTML);
    
    const modal = new bootstrap.Modal(document.getElementById('orderModal'));
    modal.show();
   
    document.getElementById('quantity').addEventListener('input', function() {
        const qty = parseInt(this.value) || 1;
        const total = price * qty;
        document.getElementById('totalPrice').textContent = total;
    });
}


function placeOrder() {
    const form = document.getElementById('orderForm');
    
  
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    
    const orderData = {
        productId: parseInt(document.getElementById('productId').value),
        productName: document.getElementById('productName').value,
        customerName: document.getElementById('customerName').value,
        customerEmail: document.getElementById('customerEmail').value,
        customerPhoneNo: document.getElementById('customerPhoneNo').value,
        address: document.getElementById('address').value,
        quantity: parseInt(document.getElementById('quantity').value),
        totalPrice: parseFloat(document.getElementById('totalPrice').textContent),
        status: 'pending',
        orderDate: new Date().toISOString()
    };
  
    fetch('http://localhost:8080/api/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
        
            const modal = bootstrap.Modal.getInstance(document.getElementById('orderModal'));
            modal.hide();
            
            alert('Order placed successfully! We will contact you soon.');
        } else {
            throw new Error('Order failed');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to place order. Please try again.');
    });
}
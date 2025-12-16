fetch("http://localhost:8080/api/products")
   .then(res=> res.json())
   .then(products=>{
    const container=document.getElementById("product-list");
    container.innerHTML="";
    products.forEach(p=>{
        container.innerHTML += `
        <div class="col-md-4 mb-4">
        <div class="card h-100">
     <img src="http://localhost:8080${p.img}" class="card-img-top" alt="${p.name}">
            <div class="card-body">
              <h5 class="card-title">${p.name}</h5>
              <p class="card-text">₹${p.price}</p>
              <button class="btn btn-success">Add to Cart</button>
            </div>
          </div>
        </div>
      `;
    });
  })
  .catch(err => console.error(err));
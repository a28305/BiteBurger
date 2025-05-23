document.addEventListener("DOMContentLoaded", async () => {
  const params = new URLSearchParams(window.location.search);
  const id_producto = params.get("id_producto");

  if (!id_producto) {
    document.getElementById("detalle-producto").innerHTML = "<p>Product not found.</p>";
    return;
  }

  try {
    const resProducto = await fetch(`http://localhost:8080/Proyecto/Controller?ACTION=PRODUCTO.FIND_BY_ID&id_producto=${id_producto}`);
    if (!resProducto.ok) throw new Error("Error fetching product");
    const product = await resProducto.json();

    const resIngredientes = await fetch(`http://localhost:8080/Proyecto/Controller?ACTION=INGREDIENTE.FIND_BY_PRODUCT&id_producto=${id_producto}`);
    if (!resIngredientes.ok) throw new Error("Error fetching ingredients");
    const ingredients = await resIngredientes.json();

    showProductDetails(product, ingredients);
  } catch (error) {
    console.error("Error:", error);
    document.getElementById("detalle-producto").innerHTML = "<p>Error loading the product.</p>";
  }
});

function showProductDetails(prod, ingredients) {
  const container = document.getElementById("detalle-producto");

  let ingredientsListHTML = "";
  if (Array.isArray(ingredients) && ingredients.length > 0) {
    ingredientsListHTML = ingredients.map(ing => `
      <div class="ingrediente-item">
        <img src="../../Imagenes/${ing.imagen}" alt="${ing.nombre}" />
        <span>${ing.nombre}</span>
      </div>
    `).join("");
  } else {
    ingredientsListHTML = "<p>No ingredients available.</p>";
  }

  container.innerHTML = `
    <div class="detalle-grid">
      <div class="detalle-izquierda">
        <img src="../../Imagenes/${prod.imagenes}" alt="${prod.nombre}" class="detalle-img" />
        <p class="detalle-descripcion">${prod.descripcion || "No description available"}</p>
        <p class="detalle-precio"><strong>Price:</strong> $${prod.precio?.toFixed(2)}</p>
        <button onclick="addToCartFromDetails(${prod.idProducto ?? prod.id_producto ?? prod.id})">
          Add to Cart
        </button>
      </div>
      <div class="detalle-derecha">
        <h3>Ingredients:</h3>
        <div class="ingredientes-contenedor">
          ${ingredientsListHTML}
        </div>
      </div>
    </div>
  `;
}

function addToCartFromDetails(id) {
  fetch(`http://localhost:8080/Proyecto/Controller?ACTION=PRODUCTO.FIND_BY_ID&id_producto=${id}`)
    .then(res => res.json())
    .then(product => {
      agregarProductoAlCarrito(product); // This function must exist in carrito.js
    })
    .catch(err => console.error("Error adding to cart", err));
}
document.addEventListener("DOMContentLoaded", () => {
    const carritoSection = document.getElementById("carrito-section");
    const carritoIcon = document.querySelector(".btn2 img"); // Asegurando que seleccionamos la imagen dentro de .btn2

    if (carritoIcon && carritoSection) {
        carritoIcon.addEventListener("click", () => {
            console.log("Clic detectado en el carrito");
            carritoSection.style.display = carritoSection.style.display === "none" ? "block" : "none";
        });
    }
});
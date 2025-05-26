const BASE_URL = "http://localhost:8080/Proyecto/Controller";

let globalProducts = [];
let globalIngredients = [];
let showingIngredients = false;

async function fetchAllProducts() {
  try {
    const res = await fetch(`${BASE_URL}?ACTION=PRODUCTO.FIND_ALL`);
    const data = await res.json();
    globalProducts = data;
    return data;
  } catch (err) {
    console.error("Error fetching products:", err);
    return [];
  }
}

async function fetchAllIngredients() {
  try {
    const res = await fetch(`${BASE_URL}?ACTION=INGREDIENTE.FIND_ALL`);
    const data = await res.json();
    globalIngredients = data;
    return data;
  } catch (err) {
    console.error("Error fetching ingredients:", err);
    return [];
  }
}

function renderProducts(products) {
  const container = document.getElementById("productos-container");
  container.innerHTML = "";

  products.forEach(prod => {
    const productId = prod.id || prod.id_producto || prod.idProducto;
    if (!productId) {
      console.warn("Product without ID, skipped:", prod);
      return;
    }

    const div = document.createElement("div");
    div.classList.add("product-card");

    div.innerHTML = `
      <img src="../../Imagenes/${prod.imagenes || 'default.jpg'}" alt="${prod.nombre || 'Product'}" class="product-image" />
      <h3>${prod.nombre || 'No name'}</h3>
      <p><strong>Price:</strong> $${prod.precio?.toFixed(2) || "0.00"}</p>
    `;

    div.addEventListener("click", () => {
      // Redirect to details.html passing id_producto as query param
      window.location.href = `../detalles/detalles.html?id_producto=${encodeURIComponent(productId)}`;
    });

    container.appendChild(div);
  });
}

function renderIngredients(ingredients) {
  const container = document.getElementById("productos-container");
  container.innerHTML = "";
  if (!ingredients.length) {
    container.innerHTML = "<p>No ingredients available.</p>";
    return;
  }

  ingredients.forEach(ing => {
    const div = document.createElement("div");
    div.className = "product-card";
    div.dataset.id = ing.id;

    div.innerHTML = `
      <img src="../../Imagenes/${ing.imagen || 'default.jpg'}" alt="${ing.nombre}" class="product-image" />
      <h3>${ing.nombre}</h3>
    `;

    div.addEventListener("click", () => {
      window.location.href = `detalleIngrediente.html?id=${ing.id}`;
    });

    container.appendChild(div);
  });
}

function filterProductsByCategory(category) {
  showingIngredients = false;

  const filtered =
    category === "burgers"
      ? globalProducts.filter(p => p.categoria?.toLowerCase() === "hamburguesa")
      : category === "bebidas"
      ? globalProducts.filter(p => p.categoria?.toLowerCase() === "bebida")
      : globalProducts;

  renderProducts(filtered);
}

document.addEventListener("DOMContentLoaded", async () => {
  const products = await fetchAllProducts();
  renderProducts(products);
});

document.getElementById("search-bar").addEventListener("input", async (e) => {
  const text = e.target.value.trim();

  if (showingIngredients) {
    const filtered = globalIngredients.filter(i =>
      i.nombre.toLowerCase().includes(text.toLowerCase())
    );
    renderIngredients(filtered);
    return;
  }

  if (text === "") {
    renderProducts(globalProducts);
    return;
  }

  try {
    const res = await fetch(`${BASE_URL}?ACTION=PRODUCTO.FIND_BY_NAME&nombre=${encodeURIComponent(text)}`);
    const data = await res.json();
    renderProducts(data);
  } catch (err) {
    console.error("Error searching product:", err);
  }
});

document.querySelectorAll(".filter-btn").forEach(btn => {
  btn.addEventListener("click", async () => {
    document.querySelectorAll(".filter-btn").forEach(b => b.classList.remove("active"));
    btn.classList.add("active");

    const filter = btn.dataset.filter;

    if (filter === "ingredientes") {
      showingIngredients = true;
      const ingredients = globalIngredients.length
        ? globalIngredients
        : await fetchAllIngredients();
      renderIngredients(ingredients);
    } else {
      filterProductsByCategory(filter);
    }
  });
});

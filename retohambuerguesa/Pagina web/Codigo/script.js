const burger = document.getElementById('burger');
const navbar = document.getElementById('navbar');

burger.addEventListener('click', () => {
  navbar.classList.toggle('hidden');
});

fetch('http://localhost:8000/backend/obtener_productos.php')
.then(response => response.json())
.then(data => {
  const productosContainer = document.getElementById('productos-container');

  if (data.length === 0) {
    productosContainer.innerHTML = "<p>No hay productos disponibles en este momento.</p>";
    return;
  }

  let contenidoHTML = "";
  const productosAleatorios = data.sort(() => 0.5 - Math.random()).slice(0, 4);

  productosAleatorios.forEach(producto => {
    contenidoHTML += `
      <div class="card">
        <img src="${producto.imagenes}" alt="${producto.nombre}">
        <h3>${producto.nombre}</h3>
        <p>Precio: $${producto.precio}</p>
        <p class="${producto.disponibilidad === 'Agotado' ? 'agotado' : 'disponible'}">
          ${producto.disponibilidad}
        </p>
      </div>
    `;
  });

  productosContainer.innerHTML = contenidoHTML;
})
.catch(error => {
  console.error('Error en la API:', error);
  document.getElementById('productos-container').innerHTML = "<p>Error al cargar los productos. Intenta de nuevo más tarde.</p>";
});



// Información de las ubicaciones
const locations = [
  {
    name: 'BiteBurger Downtown',
    address: '123 Main St, Springfield',
    description: 'Experience the best in the heart of the city. We serve up juicy burgers with a side of urban energy.',
    lat: 42.088416377191976,
    lon: -72.57834517417473
  },
  {
    name: 'BiteBurger East Side',
    address: '456 Elm St, River City',
    description: 'Enjoy a laid-back vibe and delicious bites at our East Side location, just a block from the park.',
    lat: 42.11108636178267,
    lon: -72.62506017629939
  },
  {
    name: 'BiteBurger West Hills',
    address: '789 Oak Ave, Hilltown',
    description: 'Take in the view while savoring our mouth-watering burgers in the scenic West Hills area.',
    lat: 40.910666850246734,
    lon: -74.05976185887883
  }
];

let map;

// Inicializar el mapa
function initMap() {
  // Crear el mapa centrado en la primera ubicación
  map = L.map('map').setView([locations[0].lat, locations[0].lon], 13);

  // Usar OpenStreetMap como proveedor de mapas
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);

  // Crear un marcador para cada ubicación
  locations.forEach(loc => {
    const marker = L.marker([loc.lat, loc.lon]).addTo(map);
    marker.bindPopup(`
      <strong>${loc.name}</strong><br>
      ${loc.description}<br>
      <strong>Address:</strong> ${loc.address}
    `);
  });

  // Hacer que el mapa sea más interactivo
  map.on('click', function(e) {
    map.setView(e.latlng, 13);  // Zoom in cuando se hace clic en el mapa
  });
}

// Llamar a la función para inicializar el mapa
document.addEventListener("DOMContentLoaded", function () {
  initMap();
});



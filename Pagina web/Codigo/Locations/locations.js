const locations = [
  {
    name: 'BiteBurger NY',
    address: 'New York, NY',
    description: 'Enjoy the bold flavors of the city with our juicy burgers in the heart of NYC.',
    lat: 40.81066838763326,
    lon: -73.94392429254744
  },
  {
    name: 'BiteBurger LA',
    address: 'Los Angeles, CA',
    description: 'Classic California-style burgers made with fresh ingredients, served under the sunny LA skies.',
    lat: 34.06312382394681,
    lon: -118.35096670740225
  },
  {
    name: 'BiteBurger Chicago',
    address: 'Chicago, IL',
    description: 'Savor the taste of Chicago with our hearty and flavorful burgers, crafted to perfection.',
    lat: 41.876702267490785,
    lon: -87.62949253237743
  },
  {
    name: 'BiteBurger Houston',
    address: 'Houston, TX',
    description: 'Texas-sized burgers packed with flavor, offering the best of Houston’s bold culinary style.',
    lat: 29.75979669390237,
    lon: -95.36318267459042
  },
  {
    name: 'BiteBurger Phoenix',
    address: 'Phoenix, AZ',
    description: 'Delicious burgers served with a desert twist—perfect for the Phoenix heat.',
    lat: 33.45084701448213,
    lon: -112.07472790331647
  },
  {
    name: 'BiteBurger Miami',
    address: 'Miami, FL',
    description: 'Taste the tropical flavors of Miami with burgers made for sunny beach days and vibrant nights.',
    lat: 25.76358309957362,
    lon: -80.19733223236433
  }
];
  let map;
  
  // Inicializar el mapa
  function initMap() {
    map = L.map('map').setView([locations[0].lat, locations[0].lon], 5);
  
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);
  
    locations.forEach(loc => {
      L.marker([loc.lat, loc.lon])
        .addTo(map)
        .bindPopup(`<strong>${loc.name}</strong><br>${loc.description}<br><strong>Address:</strong> ${loc.address}`);
    });
  }
  
  document.addEventListener("DOMContentLoaded", initMap);
  //BOTON PAL VER MAS
    function mostrarOpciones() {
        let opciones = document.querySelector(".extra-opciones");
        opciones.style.display = opciones.style.display === "none" ? "block" : "none";
    }
    //para controlar el desplegable
    document.getElementById("toggleMenu").addEventListener("click", function() {
      let menu = document.getElementById("menuOpciones");
      
      // Alterna entre mostrar y ocultar
      if (menu.style.display === "none" || menu.style.display === "") {
          menu.style.display = "block";
      } else {
          menu.style.display = "none";
      }
  });
  

  
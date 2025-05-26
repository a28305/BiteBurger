document.addEventListener('DOMContentLoaded', () => {
    const filter = document.getElementById('stateFilter');
    const cards = document.querySelectorAll('.job-card');
  
    // Filtrado por estado
    filter.addEventListener('change', () => {
      const val = filter.value;
      cards.forEach(card => {
        card.style.display = (val === 'all' || card.dataset.state === val) ? 'block' : 'none';
        // cierrar detalles al filtrar
        card.querySelector('.job-details').style.display = 'none';
      });
    });
    filter.dispatchEvent(new Event('change'));
  
    // Toggle de detalles en cada tarjeta
    cards.forEach(card => {
      const summary = card.querySelector('.job-summary');
      const details = card.querySelector('.job-details');
  
      summary.addEventListener('click', () => {
        const isVisible = details.style.display === 'block';
        // ocultar todo antes
        document.querySelectorAll('.job-details').forEach(d => d.style.display = 'none');
        // alternar en este
        details.style.display = isVisible ? 'none' : 'block';
      });
    });
  });
  function toggleDetails(card) {
    const details = card.querySelector(".job-details");
    details.style.display = details.style.display === "block" ? "none" : "block";
  }
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
  
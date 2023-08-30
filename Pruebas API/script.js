document.addEventListener("DOMContentLoaded", function () {
  const newsAPI = document.querySelector("#news-list")

    const comentarios = fetch("http://localhost:8080/api/newsList");
    
    comentarios
      .then((res) => res.json())
      .then((data) => {
        console.log(data[0].title);
        
    
        for (const news of data) {
          // itero el array data con un for of que crea
          // un objeto temporal de nombre elemento
          console.log(news.title);
          const nuevoDiv = document.createElement("div"); //creo un nuevo div para contener el resto del codigo
          nuevoDiv.setAttribute("class", "col");
          nuevoDiv.innerHTML = ` 
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">${news.title}</h5>
             <p class="card-text">
               "${news.body}"
              </p>
             <img src="http://localhost:8080/image/news/${news.id}" alt="News Image">
            </div>
          </div> `;
          newsAPI.appendChild(nuevoDiv); //inserto el nuevo div con todos los datos extraidos del array
        }
      })
    })
    
//     document.addEventListener("DOMContentLoaded", function () {
//       const form = document.getElementById("formLogin");

//       form.addEventListener("submit", function (event) {
//           event.preventDefault(); // Evita la recarga de la página por defecto

//           const formData = new FormData(form);

//           // Puedes agregar más validaciones aquí si es necesario

//           fetch("http://localhost:8080/api/postNews", {
//             method: "POST",
//             body: formData,
//         })
//             .then(response => {
//                 if (response.ok) {
//                     return response.text(); // Leer el cuerpo de la respuesta si la solicitud fue exitosa
//                 } else {
//                     throw new Error("Error en la solicitud");
//                 }
//             })
//             .then(data => {
//                 console.log("Respuesta del servidor:", data); // Hacer algo con la respuesta del servidor
//             })
//             .catch(error => {
//                 console.error("Error:", error); // Manejar errores de red u otros errores
//             });
        
//   });

// })





 


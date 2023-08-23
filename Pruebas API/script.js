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
    });
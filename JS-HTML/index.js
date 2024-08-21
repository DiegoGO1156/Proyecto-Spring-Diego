
 
/*const img = document.createElement('img')
img.src = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRldJnr86KD7awnVdh4270vXq04cZX4WORhVg&s'
document.body.append(img)
*/
const reqCa = fetch('http://localhost:8087/categorias').then((resp)=>{
  resp.json().then((data)=>{
    const [{categoriaid, nombreCategoria}] =data
    console.log(categoriaid, nombreCategoria)

    const idDOM = document.getElementById("categoriaid")
    idDOM.innerHTML= categoriaid
    const nombreDom = document.getElementById("nombreCategoria")
    nombreDom.innerHTML = nombreCategoria

  })  
}).catch(console.warn)

const reqC2 = fetch('http://localhost:8087/categorias').then((resp)=>{
  resp.json().then((data)=>{
    //console.log(data)

    const listaCategoria = document.getElementById("listaCategoria")

    data.map(({categoriaid, nombreCategoria})=>{
      const li = document.createElement("li")
      li.innerHTML = `Id: ${categoriaid} || Categoria: ${nombreCategoria}`
      listaCategoria.appendChild(li)
    })
  })  
}).catch(console.warn)


let {}= reqCa

  // console.log(reqCat)
// // Data artis dan konser (dummy untuk testing)
// const artist = {
//     name: "Artis 1",
//     image: "artist1.jpg",
//     description:
//       "Artis ini dikenal dengan genre pop dan telah menggelar banyak konser terkenal di dunia.",
//     shows: [
//       { name: "Konser 1", image: "concert1.jpg", date: "2024-01-15" },
//       { name: "Konser 2", image: "concert2.jpg", date: "2024-03-20" },
//       { name: "Konser 3", image: "concert3.jpg", date: "2024-06-10" },
//     ],
//   };
  
//   // Tampilkan data artis
//   document.getElementById("artistName").textContent = artist.name;
//   document.getElementById("artistImage").src = `/images/${artist.image}`;
//   document.getElementById("artistDescription").textContent = artist.description;
  
//   // Tampilkan daftar konser
//   const showList = document.getElementById("showList");
//   artist.shows.forEach((show) => {
//     const card = document.createElement("div");
//     card.className = "card";
//     card.innerHTML = `
//       <img src="/images/${show.image}" alt="${show.name}">
//       <h3>${show.name}</h3>
//       <p>${show.date}</p>
//     `;
//     showList.appendChild(card);
//   });

// const successMessage = /*[[${success}]]*/ 'null';
//     const errorMessage = /*[[${error}]]*/ 'null';

//     if (successMessage !== 'null') {
//         alert(successMessage); // Show alert
//         window.location.href = '/member/home-member'; // Redirect after "OK"
//     }

//     if (errorMessage !== 'null') {
//         alert(errorMessage); // Show alert for errors
//     }

// document.addEventListener("DOMContentLoaded", () => {
//   const successMessageElement = document.getElementById("success-message");
//   const errorMessageElement = document.getElementById("error-message");

//   if (successMessageElement) {
//       const successMessage = successMessageElement.value;
//       alert(successMessage); // Tampilkan pesan sukses
//       // window.location.href = "/member/home-member"; // Redirect setelah alert sukses
//   }

//   if (errorMessageElement) {
//       const errorMessage = errorMessageElement.value;
//       alert(errorMessage); // Tampilkan pesan error
//   }
// });



  
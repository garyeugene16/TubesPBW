// // Fungsi pencarian sederhana
// document.getElementById("searchButton").addEventListener("click", function () {
//     const query = document.getElementById("searchInput").value;
  
//     // Contoh hasil pencarian sementara
//     const dummyResults = [
//       { name: "Artis 1", image: "artist1.jpg" },
//       { name: "Artis 2", image: "artist2.jpg" },
//       { name: "Konser 1", image: "concert1.jpg" },
//     ];
  
//     const resultsContainer = document.getElementById("resultsContainer");
//     resultsContainer.innerHTML = ""; // Kosongkan hasil sebelumnya
  
//     // Filter hasil pencarian
//     const filteredResults = dummyResults.filter((item) =>
//       item.name.toLowerCase().includes(query.toLowerCase())
//     );
  
//     // Tampilkan hasil
//     filteredResults.forEach((result) => {
//       const card = document.createElement("div");
//       card.className = "card";
//       card.innerHTML = `
//         <img src="/images/${result.image}" alt="${result.name}">
//         <h3>${result.name}</h3>
//       `;
//       resultsContainer.appendChild(card);
//     });
  
//     if (filteredResults.length === 0) {
//       resultsContainer.innerHTML = "<p>Hasil tidak ditemukan.</p>";
//     }
//   });
  
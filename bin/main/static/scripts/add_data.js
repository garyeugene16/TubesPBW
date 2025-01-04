// // Get form elements and buttons
// const btnAddArtist = document.getElementById("btn-add-artist");
// const btnAddShow = document.getElementById("btn-add-show");
// const btnAddSetlist = document.getElementById("btn-add-setlist");

// const formAddArtist = document.getElementById("add-artist-form");
// const formAddShow = document.getElementById("add-show-form");
// const formAddSetlist = document.getElementById("add-setlist-form");

// // Function to show one form and hide the others
// function showForm(formToShow) {
//   [formAddArtist, formAddShow, formAddSetlist].forEach((form) => {
//     if (form === formToShow) {
//       form.classList.remove("hidden");
//     } else {
//       form.classList.add("hidden");
//     }
//   });
// }

// // Add event listeners to buttons for showing forms
// btnAddArtist.addEventListener("click", () => showForm(formAddArtist));
// btnAddShow.addEventListener("click", () => showForm(formAddShow));
// btnAddSetlist.addEventListener("click", () => showForm(formAddSetlist));

// document.addEventListener("DOMContentLoaded", () => {
//   const successMessageElement = document.getElementById("success-message");
//   const errorMessageElement = document.getElementById("error-message");

//   if (successMessageElement) {
//       const successMessage = successMessageElement.value;
//       alert(successMessage); // Tampilkan pesan sukses
//       window.location.href = "/member/add-data"; // Redirect setelah alert sukses
//   }

//   if (errorMessageElement) {
//       const errorMessage = errorMessageElement.value;
//       alert(errorMessage); // Tampilkan pesan error
//       window.location.href = "/member/add-data"; // Redirect setelah alert sukses
//   }
// });

//

// Elemen Form dan Button
const btnAddArtist = document.getElementById("btn-add-artist");
const btnAddShow = document.getElementById("btn-add-show");

const formAddArtist = document.getElementById("add-artist-form");
const formAddShow = document.getElementById("add-show-form");

// Fungsi untuk menampilkan salah satu form (artist/show)
function showForm(formToShow) {
  // Daftar form yang ingin diatur
  [formAddArtist, formAddShow].forEach((form) => {
    if (form === formToShow) {
      form.classList.remove("hidden");
    } else {
      form.classList.add("hidden");
    }
  });
}

// Event Listener Button untuk menampilkan Form
btnAddArtist.addEventListener("click", () => showForm(formAddArtist));
btnAddShow.addEventListener("click", () => showForm(formAddShow));

// Bagian untuk menampilkan alert success/error (unchanged)
document.addEventListener("DOMContentLoaded", () => {
  const successMessageElement = document.getElementById("success-message");
  const errorMessageElement = document.getElementById("error-message");

  if (successMessageElement) {
      const successMessage = successMessageElement.value;
      alert(successMessage);
      window.location.href = "/member/add-data";
  }

  if (errorMessageElement) {
      const errorMessage = errorMessageElement.value;
      alert(errorMessage);
      window.location.href = "/member/add-data";
  }
});
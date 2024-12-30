// Get form elements and buttons
const btnAddArtist = document.getElementById("btn-add-artist");
const btnAddShow = document.getElementById("btn-add-show");
const btnAddSetlist = document.getElementById("btn-add-setlist");

const formAddArtist = document.getElementById("add-artist-form");
const formAddShow = document.getElementById("add-show-form");
const formAddSetlist = document.getElementById("add-setlist-form");

// Function to show one form and hide the others
function showForm(formToShow) {
  [formAddArtist, formAddShow, formAddSetlist].forEach((form) => {
    if (form === formToShow) {
      form.classList.remove("hidden");
    } else {
      form.classList.add("hidden");
    }
  });
}

// Add event listeners to buttons for showing forms
btnAddArtist.addEventListener("click", () => showForm(formAddArtist));
btnAddShow.addEventListener("click", () => showForm(formAddShow));
btnAddSetlist.addEventListener("click", () => showForm(formAddSetlist));

document.addEventListener("DOMContentLoaded", () => {
  const successMessageElement = document.getElementById("success-message");
  const errorMessageElement = document.getElementById("error-message");

  if (successMessageElement) {
      const successMessage = successMessageElement.value;
      alert(successMessage); // Tampilkan pesan sukses
      window.location.href = "/member/add-data"; // Redirect setelah alert sukses
  }

  if (errorMessageElement) {
      const errorMessage = errorMessageElement.value;
      alert(errorMessage); // Tampilkan pesan error
      window.location.href = "/member/add-data"; // Redirect setelah alert sukses
  }
});

// // Handle Add Artist Form Submission
// formAddArtist.addEventListener("submit", (e) => {
//   e.preventDefault();
//   const artistName = document.getElementById("artist-name").value;

//   fetch('/api/artists', {
//     method: 'POST',
//     headers: { 'Content-Type': 'application/json' },
//     body: JSON.stringify({ name: artistName }),
//   })
//     .then(response => response.json())
//     .then(() => alert("Artist added successfully!"))
//     .catch(error => console.error("Error:", error));
// });

// // Handle Add Show Form Submission
// formAddShow.addEventListener("submit", (e) => {
//   e.preventDefault();
//   const artistId = document.getElementById("artist-id").value;
//   const venue = document.getElementById("venue").value;
//   const date = document.getElementById("date").value;

//   fetch('/api/shows', {
//     method: 'POST',
//     headers: { 'Content-Type': 'application/json' },
//     body: JSON.stringify({ artist_id: artistId, venue, date }),
//   })
//     .then(response => response.json())
//     .then(() => alert("Show added successfully!"))
//     .catch(error => console.error("Error:", error));
// });

// // Handle Add Setlist Form Submission
// formAddSetlist.addEventListener("submit", (e) => {
//   e.preventDefault();
//   const showId = document.getElementById("show-id").value;
//   const songTitle = document.getElementById("song-title").value;
//   const proof = document.getElementById("proof").files[0];

//   const formData = new FormData();
//   formData.append("show_id", showId);
//   formData.append("song_title", songTitle);
//   if (proof) formData.append("proof", proof);

//   fetch('/api/setlists', {
//     method: 'POST',
//     body: formData,
//   })
//     .then(response => response.json())
//     .then(() => alert("Setlist added successfully!"))
//     .catch(error => console.error("Error:", error));
// });

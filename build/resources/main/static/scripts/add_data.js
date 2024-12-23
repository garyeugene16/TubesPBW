document.addEventListener('DOMContentLoaded', function() {
  const addArtistButton = document.getElementById('btn-add-artist');
  const addShowButton = document.getElementById('btn-add-show');
  const addSetlistButton = document.getElementById('btn-add-setlist');
  
  const addArtistForm = document.getElementById('add-artist-form');
  const addShowForm = document.getElementById('add-show-form');
  const addSetlistForm = document.getElementById('add-setlist-form');

  // Show form based on button click
  addArtistButton.addEventListener('click', function() {
      toggleFormVisibility(addArtistForm);
  });

  addShowButton.addEventListener('click', function() {
      toggleFormVisibility(addShowForm);
      loadArtists();  // Load artists for show
  });

  addSetlistButton.addEventListener('click', function() {
      toggleFormVisibility(addSetlistForm);
      loadShows();  // Load shows for setlist
  });

  // Toggle visibility of forms
  function toggleFormVisibility(form) {
      const forms = document.querySelectorAll('.form');
      forms.forEach(f => f.classList.add('hidden'));
      form.classList.remove('hidden');
  }

  // Load artists for Add Show form
  function loadArtists() {
      const artistSelect = document.getElementById('artist-id');
      artistSelect.innerHTML = '<option value="" disabled selected>Select an Artist</option>'; // Clear previous options
      
      // Make an AJAX request to get the list of artists (replace with actual API call)
      fetch('/api/artists')
          .then(response => response.json())
          .then(artists => {
              artists.forEach(artist => {
                  const option = document.createElement('option');
                  option.value = artist.id;
                  option.textContent = artist.name;
                  artistSelect.appendChild(option);
              });
          })
          .catch(error => console.error('Error loading artists:', error));
  }

  // Load shows for Add Setlist form
  function loadShows() {
      const showSelect = document.getElementById('show-id');
      showSelect.innerHTML = '<option value="" disabled selected>Select a Show</option>'; // Clear previous options
      
      // Make an AJAX request to get the list of shows (replace with actual API call)
      fetch('/api/shows')
          .then(response => response.json())
          .then(shows => {
              shows.forEach(show => {
                  const option = document.createElement('option');
                  option.value = show.id;
                  option.textContent = `${show.artistName} - ${show.venue}`;
                  showSelect.appendChild(option);
              });
          })
          .catch(error => console.error('Error loading shows:', error));
  }

  // Handle Add Artist form submission
  addArtistForm.addEventListener('submit', function(event) {
      event.preventDefault();
      const artistName = document.getElementById('artist-name').value;

      fetch('/api/artists', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify({ name: artistName })
      })
      .then(response => {
          if (response.ok) {
              alert('Artist added successfully!');
              addArtistForm.reset();
          } else {
              alert('Error adding artist');
          }
      })
      .catch(error => console.error('Error:', error));
  });

  // Handle Add Show form submission
  addShowForm.addEventListener('submit', function(event) {
      event.preventDefault();
      const artistId = document.getElementById('artist-id').value;
      const venue = document.getElementById('venue').value;
      const date = document.getElementById('date').value;

      fetch('/api/shows', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify({ artistId, venue, date })
      })
      .then(response => {
          if (response.ok) {
              alert('Show added successfully!');
              addShowForm.reset();
          } else {
              alert('Error adding show');
          }
      })
      .catch(error => console.error('Error:', error));
  });

  // Handle Add Setlist form submission
  addSetlistForm.addEventListener('submit', function(event) {
      event.preventDefault();
      const showId = document.getElementById('show-id').value;
      const songTitle = document.getElementById('song-title').value;
      const proof = document.getElementById('proof').files[0];

      const formData = new FormData();
      formData.append('showId', showId);
      formData.append('songTitle', songTitle);
      if (proof) {
          formData.append('proof', proof);
      }

      fetch('/api/setlists', {
          method: 'POST',
          body: formData
      })
      .then(response => {
          if (response.ok) {
              alert('Setlist added successfully!');
              addSetlistForm.reset();
          } else {
              alert('Error adding setlist');
          }
      })
      .catch(error => console.error('Error:', error));
  });
});

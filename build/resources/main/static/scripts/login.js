const formTitle = document.getElementById("formTitle");
const authForm = document.getElementById("authForm");
const submitButton = document.getElementById("submitButton");
const toggleText = document.getElementById("toggleText");

let isLogin = true; // Flag untuk login atau registrasi

// Fungsi untuk toggle antara Login dan Registrasi
document.getElementById("toggleForm").addEventListener("click", () => {
  isLogin = !isLogin;

  // Perbarui tampilan form
  if (isLogin) {
    formTitle.textContent = "Login";
    submitButton.textContent = "Login";
    toggleText.innerHTML = `Belum punya akun? <span id="toggleForm">Daftar sekarang</span>`;
  } else {
    formTitle.textContent = "Registrasi";
    submitButton.textContent = "Daftar";
    toggleText.innerHTML = `Sudah punya akun? <span id="toggleForm">Login di sini</span>`;
  }
});

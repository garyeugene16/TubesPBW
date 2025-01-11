const formTitle = document.getElementById("formTitle");
const authForm = document.getElementById("authForm");
const submitButton = document.getElementById("submitButton");
const toggleText = document.getElementById("toggleText");

let isLogin = true; // Flag untuk login atau registrasi

// // Fungsi untuk toggle antara Login dan Registrasi
// document.getElementById("toggleForm").addEventListener("click", () => {
//   isLogin = !isLogin;

//   // Perbarui tampilan form
//   if (isLogin) {
//     formTitle.textContent = "Login";
//     submitButton.textContent = "Login";
//     toggleText.innerHTML = `Belum punya akun? <span id="toggleForm">Daftar sekarang</span>`;
//   } else {
//     formTitle.textContent = "Registrasi";
//     submitButton.textContent = "Daftar";
//     toggleText.innerHTML = `Sudah punya akun? <span id="toggleForm">Login di sini</span>`;
//   }
// });

document.addEventListener('DOMContentLoaded', function() {
    const togglePasswords = document.querySelectorAll('.toggle-password');
    
    togglePasswords.forEach(toggle => {
        toggle.addEventListener('click', function() {
            const input = this.previousElementSibling || this.parentElement.querySelector('input');
            
            // Toggle tipe input
            const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
            input.setAttribute('type', type);
            
            // Toggle icon
            const icon = this.querySelector('i');
            icon.classList.toggle('fa-eye');
            icon.classList.toggle('fa-eye-slash');
        });
    });

    // Pastikan semua input password kembali ke tipe 'password' saat form disubmit
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function() {
            const passwordInputs = this.querySelectorAll('input[type="text"]');
            passwordInputs.forEach(input => {
                if (input.id === 'password' || input.id === 'confirmpassword') {
                    input.setAttribute('type', 'password');
                }
            });
        });
    });
});

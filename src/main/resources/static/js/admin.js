function showSection(id) {
    document.querySelectorAll('.content-section').forEach(section => {
        section.classList.remove('active');
    });
    document.getElementById(id).classList.add('active');

    document.querySelectorAll('.nav-btn').forEach(btn => {
        btn.classList.remove('active');
    });
    event.target.classList.add('active');
}

function toggleChangeUsername(){
    const cUnameForm = document.getElementById("changeUsername");
    const cPasswdForm = document.getElementById("changePassword");
    cPasswdForm.style.display = 'none';
    cUnameForm.style.display = cUnameForm.style.display === 'none'? 'block':'none';
}
function toggleChangePassword(){
    const cUnameForm = document.getElementById("changeUsername");
    const cPasswdForm = document.getElementById("changePassword");
    cPasswdForm.style.display = cPasswdForm.style.display === 'none'? 'block':'none';
    cUnameForm.style.display = 'none';
}
function logout(){
    document.getElementById('logout').style.display='flex';
}
function closeOverlay(id){
    document.getElementById(id).style.display='none';
}
function toggleAddBook(){
    document.getElementById('addBookOverlay').style.display = 'flex';
}
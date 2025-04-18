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
function logout(){
    document.getElementById('logout').style.display='flex';
}
function closeOverlay(id){
    document.getElementById(id).style.display='none';
}

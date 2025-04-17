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
//开启overlay界面
function toggleAddBook(){
    document.getElementById('bookOverlay').style.display = 'flex';
    document.getElementById('addbookh2').style.display = 'block';
    document.getElementById('addBookForm').style.display = 'block';
    document.getElementById('changebookh2').style.display = 'none';
    document.getElementById('changeBookForm').style.display = 'none';
}
function toggleChangeBook(bookData){

    //填充内容
    document.getElementById('changebook-name').innerText = bookData.title;
    document.getElementById('changebook-author').innerText = bookData.author;
    document.getElementById('changebook-type').innerText = bookData.type;
    document.getElementById('changebook-publisher').innerText = bookData.publisher;
    document.getElementById('changebook-number').innerText = bookData.number;
    document.getElementById('changebook-desc').innerText = bookData.desc;

    document.getElementById('bookOverlay').style.display = 'flex';
    document.getElementById('addbookh2').style.display = 'none';
    document.getElementById('addBookForm').style.display = 'none';
    document.getElementById('changebookh2').style.display = 'block';
    document.getElementById('changeBookForm').style.display = 'block';
}
function ClickBookCard(el){
    const title = el.dataset.title;
    const author = el.dataset.author;
    const type = el.dataset.type;
    const publisher = el.dataset.publisher;
    const number = el.dataset.number;
    const desc = el.dataset.desc;
    toggleChangeBook({title, author, type, publisher, number, desc});
}
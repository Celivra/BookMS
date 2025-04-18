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
    document.getElementById('changebook-name').value= bookData.title;
    document.getElementById('changebook-author').value = bookData.author;
    document.getElementById('changebook-type').value = bookData.type;
    document.getElementById('changebook-publisher').value = bookData.publisher;
    document.getElementById('changebook-number').value = bookData.number;
    document.getElementById('changebook-desc').value = bookData.desc;

    document.getElementById('bookOverlay').style.display = 'flex';
    document.getElementById('addbookh2').style.display = 'none';
    document.getElementById('addBookForm').style.display = 'none';
    document.getElementById('changebookh2').style.display = 'block';
    document.getElementById('changeBookForm').style.display = 'block';
}
function toggleDeleteBook(id){
    document.getElementById('deleteBook').style.display = 'flex';
    document.getElementById('deleteBookId').value = id;
    console.log(id);
}
function ClickBookCard(el){
    const card = el.closest('.book-card');
    const title = card.dataset.title;
    const author = card.dataset.author;
    const type = card.dataset.type;
    const publisher = card.dataset.publisher;
    const number = card.dataset.number;
    const desc = card.dataset.desc;
    toggleChangeBook({title, author, type, publisher, number, desc});
}

function ClickDeleteBook(el){
    const card = el.closest('.book-card');
    const id = card.dataset.id;
    toggleDeleteBook(id);
}
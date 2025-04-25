function toggleChangeUsername(){
    const cUnameForm = document.getElementById("changeUsername");
    const cPasswdForm = document.getElementById("changePassword");
    cPasswdForm.style.display = 'none';
    cUnameForm.style.display = cUnameForm.style.display === 'none'? 'block':'none';
}
function toggleChangePassword(){
    const cPasswdForm = document.getElementById("changePassword");
    cPasswdForm.style.display = cPasswdForm.style.display === 'none'? 'block':'none';
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
    document.getElementById('changebook-id').value= bookData.id;
    document.getElementById('changebook-isbn').value= bookData.isbn;
    document.getElementById('changebook-name').value= bookData.title;
    document.getElementById('changebook-author').value = bookData.author;
    document.getElementById('changebook-type').value = bookData.type;
    document.getElementById('changebook-publisher').value = bookData.publisher;
    document.getElementById('changebook-publisheddate').value = bookData.publisheddate;
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
}
function toggleUpdateUser(userData){
    document.getElementById('updateUser-id').value = userData.id;
    document.getElementById('updateUser-username').value = userData.username;
    document.getElementById('updateUser-phone').value = userData.phone;
    document.getElementById('updateUser-email').value = userData.email;
    document.getElementById('updateUser-power').checked = (userData.power ==='10')
    document.getElementById('UserOverlay').style.display = 'flex'

}
function toggleDeleteUser(id){
    document.getElementById('deleteUser').style.display = 'flex';
    document.getElementById('deleteUserId').value = id;
}
function ClickBookCard(el){
    const card = el.closest('.book-card');
    const isbn = card.dataset.isbn
    const id = card.dataset.id
    const title = card.dataset.title;
    const author = card.dataset.author;
    const type = card.dataset.type;
    const publisher = card.dataset.publisher;
    const publisheddate = card.dataset.publisheddate;
    const number = card.dataset.number;
    const desc = card.dataset.desc;
    toggleChangeBook({id, isbn,title, author, type, publisher, publisheddate, number, desc});
}

function ClickDeleteBook(el){
    const card = el.closest('.book-card');
    const id = card.dataset.id;
    toggleDeleteBook(id);
}
function ClickDeleteUser(el){
    const card = el.closest('.user-card');
    const id = card.dataset.id;
    toggleDeleteUser(id);
}

function ClickUser(el){
    const user = el.closest('.user-card')
    const id = user.dataset.id
    const username = user.dataset.username
    const phone =user.dataset.phone
    const email = user.dataset.email
    const power = user.dataset.power
    toggleUpdateUser({id, username, phone, email, power})

}
window.onload = function () {
    if (typeof activeSection !== "undefined" && activeSection !== null) {
        showSection(activeSection);
    }
};

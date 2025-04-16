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

function logout() {
    alert("已退出登录！");
    window.location.href = "/";
}

function toggleEditForm() {
    const form = document.getElementById('editForm');
    const passwdform = document.getElementById('editPasswordForm');
    passwdform.style.display = 'none';
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
}
function toggleEditPasswdForm() {
    const form = document.getElementById('editForm');
    const passwdform = document.getElementById('editPasswordForm');
    form.style.display = 'none';
    passwdform.style.display = passwdform.style.display === 'none' ? 'block' : 'none';
}
function toggleBorrowBookDetail(bookData) {
    // 填充内容
    document.getElementById('borrow-title').innerText = "《"+bookData.title+"》";
    document.getElementById('borrow-author').innerText = "作者：" + bookData.author;
    document.getElementById('borrow-type').innerText = "类型：" + bookData.type;
    document.getElementById('borrow-publisher').innerText = "出版社：" + bookData.publisher;
    document.getElementById('borrow-number').innerText = "所剩数量：" + bookData.number;
    document.getElementById('borrow-desc').innerText = "简介：" + bookData.desc;

    document.getElementById('borrowBookId').value = bookData.bookid;

    // 显示遮罩层
    document.getElementById('borrowOverlay').style.display = 'flex';
    const borrowbutton =  document.getElementById('borrowButton');
    borrowbutton.disabled = (bookData.number == 0);
}

function closeBorrowedOverlay() {
    document.getElementById('Borrowed').style.display = 'none';
}
function closeBorrowOverlay() {
    document.getElementById('borrowOverlay').style.display = 'none';
}

document.querySelector("#editPasswordForm form").addEventListener("submit", function (e) {
    const password1 = document.getElementById("password1").value;
    const password2 = document.getElementById("password2").value;

    if (password1 !== password2) {
        e.preventDefault(); // 阻止表单提交
        alert("两次输入的密码不一致，请重新输入。");
        return false;
    }else{
        alert("修改成功");
        return true;
    }
});
function ClickBookCard(el){
    const bookid = el.dataset.bookid
    const title = el.dataset.title;
    const author = el.dataset.author;
    const type = el.dataset.type;
    const publisher = el.dataset.publisher;
    const number = el.dataset.number;
    const desc = el.dataset.desc;
    toggleBorrowBookDetail({bookid, title, author, type, publisher, number, desc});
}
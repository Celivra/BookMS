function toggleEditUserForm(flag){
    const form = document.getElementById('editForm');
    const passwdform = document.getElementById('editPasswordForm');
    if(flag === 'password'){
        form.style.display = 'none';
        passwdform.style.display = passwdform.style.display === 'none' ? 'block' : 'none';
    }else{
        passwdform.style.display = 'none';
        form.style.display = form.style.display === 'none' ? 'block' : 'none';
    }
}
//显示借阅书籍跟归还书籍的界面
function toggleBookDetail(bookData, BorR) {
    //显示哪一个form
    document.getElementById('borrowForm').style.display = BorR==='borrow'?'block':'none';
    document.getElementById('returnForm').style.display = BorR==='return'?'block':'none';

    //当显示returnform时关闭哪些标签
    document.getElementById('overlay-number').style.display = BorR==='borrow'?'block':'none';

    // 填充内容
    document.getElementById('overlay-title').innerText = "《"+bookData.title+"》";
    document.getElementById('overlay-ISBN').innerText = "ISBN:"+bookData.isbn;
    document.getElementById('overlay-author').innerText = "作者：" + bookData.author;
    document.getElementById('overlay-type').innerText = "类型：" + bookData.type;
    document.getElementById('overlay-publisher').innerText = "出版社：" + bookData.publisher;
    const dateStr =  bookData.publisheddate;
    console.log("second:"+dateStr);
    if (dateStr) {
        const [year, month, day] = dateStr.split("-");
        document.getElementById("overlay-publishedDate").innerText =
            `出版日期：${year}年${month}月${day}日`;
    } else {
        document.getElementById("overlay-publishedDate").innerText = "出版日期：暂无";
    }
    document.getElementById('overlay-number').innerText = "所剩数量：" + bookData.number;
    document.getElementById('overlay-desc').innerText = "简介：" + bookData.desc;

    document.getElementById('borrowBookId').value = bookData.bookid;
    document.getElementById('returnbookid').value = bookData.bookid;

    // 显示遮罩层
    document.getElementById('overlay').style.display = 'flex';
    const borrowbutton =  document.getElementById('borrowButton');
    borrowbutton.disabled = (bookData.number == 0);
}
//判断输入的两个密码是否相同
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
//点击书籍卡片时接受点击的书籍的信息
function ClickBookCard(el, BorR){
    const bookid = el.dataset.id
    const isbn = el.dataset.isbn
    const title = el.dataset.title;
    const author = el.dataset.author;
    const type = el.dataset.type;
    const publisher = el.dataset.publisher;
    const publisheddate = el.dataset.publisheddate;
    const number = el.dataset.number;
    const desc = el.dataset.desc;
    console.log("first:"+publisheddate);
    toggleBookDetail({bookid, isbn, title, author, type, publisher, publisheddate, number, desc}, BorR);
}
window.onload = function () {
    if (typeof activeSection !== "undefined" && activeSection !== null) {
        showSection(activeSection);
    }
};

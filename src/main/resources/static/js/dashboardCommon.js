/*==================当页面加载的时候判断后端有无在model里设置activeSection=================*/
window.onload = function () {
    if (typeof activeSection !== "undefined" && activeSection !== null) {
        showSection(activeSection);
    }
};
/*=====================================判断结束=======================================*/

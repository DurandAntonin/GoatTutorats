document.addEventListener("DOMContentLoaded", function () {
    const toastElList = [].slice.call(document.querySelectorAll('.toast'));
    toastElList.map(function (toastEl) {
        const toast = new bootstrap.Toast(toastEl, { delay: 3000 });
        toast.show();

        toastEl.addEventListener('hide.bs.toast', function () {
            toastEl.classList.add('fade-out');

            setTimeout(() => {
                toastEl.classList.remove('fade-out');
            }, 500);
        });
    });
});
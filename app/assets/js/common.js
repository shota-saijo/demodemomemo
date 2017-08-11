$(document).ready(function() {
  $('tr.table-link').on('click', function() {
    var link = $(this).data('html');
    window.location.href = link;
  });
})

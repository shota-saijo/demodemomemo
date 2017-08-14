$(document).ready(function() {
  $('tr.table-link').on('click', function() {
    var link = $(this).data('html');
    window.location.href = link;
  });

  $('input[type="checkbox"]').prettyCheckable();
  $('input[type="radio"]').prettyCheckable();

  autosize($('textarea'));
})

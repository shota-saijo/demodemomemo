$(document).ready(function() {
  $('tr.table-link').on('click', function() {
    var link = $(this).data('html');
    window.location.href = link;
  });

  $('input[type="checkbox"]').prettyCheckable();
  $('input[type="radio"]').prettyCheckable();

  autosize($('textarea'));

  $('input.datetimepicker').datetimepicker({
    format: 'YYYY-MM-DD',
    useCurrent: !$('input.datetimepicker').hasClass('end')
  });

  $('input.datetimepicker.start').on("dp.change", function(e) {
    $('input.datetimepicker.end').data("DateTimePicker").minDate(e.date);
  });

  $('input.datetimepicker.end').on("dp.change", function(e) {
    $('input.datetimepicker.start').data("DateTimePicker").maxDate(e.date);
  });

})

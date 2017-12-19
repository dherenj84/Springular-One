myApp.directive("formatPhone", function() {
    return {
        restrict: "A",
        link: function(scope, element, attrs) {
            $(element).on(
                "input",
                function() {
                    var number = $(this).val().replace(/[^\d]/g, '')
                    if (number.length == 7) {
                        number = number.replace(/(\d{3})(\d{4})/, "$1-$2");
                    } else if (number.length == 10) {
                        number = number.replace(/(\d{3})(\d{3})(\d{4})/,
                            "($1) $2-$3");
                    }
                    $(this).val(number)
                });
        }
    }
});

myApp.directive("formatText", function() {
    return {
        restrict: "A",
        link: function(scope, element, attrs) {
            $(element).on("input", function() {
                var text = $(this).val().replace(/[^a-z\s]/gi, '');
                $(this).val(text);
            });
            $(element).on("change", function() {
                var text = $(this).val().replace(/^\s+|\s+$/g, '');
                $(this).val(text);
            });
        }
    }
});

myApp.directive("formatNumber", function($filter) {
    return {
        restrict: "A",
        link: function(scope, element, attrs) {
            $(element).on("input", function() {
                var number = $(this).val().replace(/[^\d]/g, '')
                $(this).val(number);
            });

            /*$(element).on("change", function() {
            	var text = $filter('number')($(this).val(), 0)
            	$(this).val(text);
            });*/
        }
    }
});

myApp.directive("formatDecimal", function($filter) {
    return {
        restrict: "A",
        link: function(scope, element, attrs) {
            $(element).on("input", function() {
                $(element.parent().next("small").addClass("ng-hide"));
                var isDecimal = $(this).val().match(/^(?=.?\d)\d*(\.\d{0,2})?$/);
                if (!isDecimal) {
                    $(element.parent().next("small").removeClass("ng-hide"));
                }
            });

            $(element).on("change", function() {
                $(this).val($filter('currency')($(this).val(), "", 2));
            });
        }
    }
});
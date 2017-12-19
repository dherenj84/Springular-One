myApp.component('grid', {
    templateUrl: 'resources/app/components/grid.component.html',
    bindings: {
        gridOptions: '='
    },
    controller: function dataGridController($scope, $rootScope, $filter) {
        var ctrl = this;

        $scope.$watch("$ctrl.gridOptions", function(newVal, oldVal) {
            if (ctrl.gridOptions != undefined &&
                ctrl.gridOptions.data.length > 0) {
                if (ctrl.gridOptions.orderPredicate) {
                    ctrl.orderProp = ctrl.gridOptions.orderPredicate;
                    if (ctrl.gridOptions.order &&
                        ctrl.gridOptions.order != 'asc')
                        ctrl.reverse = true;
                }
                ctrl.renderList(ctrl.gridOptions.data, ctrl.orderProp,
                    ctrl.reverse);
            }
        });

        this.renderList = function(data, orderProp, reverse) {
            if (orderProp) {
                if (reverse)
                    this.gridOptions.data = $filter("orderBy")(data,
                        orderProp, true);
                else
                    this.gridOptions.data = $filter("orderBy")(data,
                        orderProp);
            }
            this.gridDataCopy = angular.copy(this.gridOptions.data);
            this.rowCount = data.length;
            this.pages = Math.floor(this.rowCount / this.pageSize);
            this.rem = this.rowCount % this.pageSize;
            this.pageList = [];
            var pageId = 1;
            var index = 0;
            var loopCount = this.pages;
            if (loopCount > 0) {
                while (loopCount > 0) {
                    this.pageList.push({
                        pageId: pageId,
                        startIndex: index
                    });
                    pageId += 1;
                    index = index + this.pageSize;
                    loopCount -= 1;
                }
                if (this.rem > 0) {
                    this.pages += 1;
                    this.pageList.push({
                        pageId: pageId,
                        startIndex: index
                    });
                }
            } else {
                this.pageList.push({
                    pageId: pageId,
                    startIndex: index
                });
            }
            this.goToPage(this.currentPage);
        };

        this.getVal = function(item, column) {
            if (column.nested) {
                var nestings = column.jsonRef.split(".");
                angular.forEach(nestings, function(nesting) {
                    item = item[nesting];
                });
                if (column.type == "date")
                    return $filter("date")(item, "short");
                else
                    return item;
            } else {
                if (column.type == "date")
                    return $filter("date")(item[column.jsonRef], "short");
                else
                    return item[column.jsonRef];
            }
        };

        this.setPageSize = function(size) {
            this.pageSize = size;
            this.renderList(this.gridDataCopy, this.orderProp, this.reverse);
            //this.goToPage(1, 0);
        };

        this.$onInit = function() {
            this.pageSize = 10;
            this.currentPage = 1;
            this.currentStartIndex = 0;
            this.first = true;
            this.rowCount = 0;
        };

        this.getPageIndex = function(pageId) {
            var startIndex = 0;
            for (x in this.pageList) {
                var page = this.pageList[x];
                if (page.pageId == pageId) {
                    startIndex = page.startIndex;
                    break;
                }
            }
            return startIndex;
        };

        this.goToPage = function(pageId) {
            this.currentPage = pageId;
            this.currentStartIndex = this.getPageIndex(pageId);
            this.gridOptions.data = $filter('limitTo')(this.gridDataCopy,
                this.pageSize, this.currentStartIndex);
            if (pageId == 1) {
                this.first = true;
                this.last = false;
            } else if (pageId == this.pages) {
                this.first = false;
                this.last = true;
            } else {
                this.first = false;
                this.last = false;
            }
        };

        this.goToPageDirection = function(direction) {
            if (direction == 'p')
                this.goToPage(this.currentPage - 1, this.currentStartIndex -
                    this.pageSize);
            else if (direction == 'n')
                this.goToPage(this.currentPage + 1, this.currentStartIndex +
                    this.pageSize);
            else if (direction == 'f')
                this.goToPage(1, 0);
            else if (direction == 'l')
                this.goToPage(this.pages, this.rem > 0 ? this.rowCount -
                    this.rem : this.rowCount - this.pageSize);
        };

        this.orderBy = function(orderProp, element, reverse) {
            this.orderProp = orderProp;
            this.reverse = reverse;
            $(element).closest("th").addClass('sorted');
            $(element).closest("thead").find("th.sortable").not($(element).closest("th")).removeClass('sorted');
            $(element).closest("thead").find("th.sortable").not($(element).closest("th")).children("button.sort").removeClass('ng-hide');
            $(element).closest("thead").find("th.sortable").not($(element).closest("th")).children("button.sort-asc, button.sort-desc").addClass('ng-hide');
            $(element).addClass('ng-hide');
            if ($(element).hasClass("sort")) {
                $(element).closest("th").find(".sort-asc").removeClass(
                    'ng-hide');
            } else if ($(element).hasClass("sort-asc")) {

                $(element).closest("th").find(".sort-desc").removeClass(
                    'ng-hide');

            } else if ($(element).hasClass("sort-desc")) {
                $(element).closest("th").find(".sort-asc").removeClass(
                    'ng-hide');
            }
            this.renderList(this.gridDataCopy, orderProp, reverse);
        }
    }
});
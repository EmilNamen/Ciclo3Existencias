define(['model/existenciasModel'], function(existenciasModel) {
    App.Controller._ExistenciasController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#existencias').html());
            this.listTemplate = _.template($('#existenciasList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'existencias-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'existencias-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'existencias-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'existencias-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-existencias-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'existencias-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit(options);
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-existencias-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-existencias-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-existencias-create', {view: this});
                this.currentExistenciasModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-existencias-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-existencias-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-existencias-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-existencias-list', {view: this, data: data});
                var self = this;
				if(!this.existenciasModelList){
                 this.existenciasModelList = new this.listModelClass();
				}
                this.existenciasModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-existencias-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'existencias-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-existencias-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-existencias-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-existencias-edit', {view: this, id: id, data: data});
                if (this.existenciasModelList) {
                    this.currentExistenciasModel = this.existenciasModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-existencias-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentExistenciasModel = new this.modelClass({id: id});
                    this.currentExistenciasModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-existencias-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'existencias-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-existencias-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-existencias-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-existencias-delete', {view: this, id: id});
                var deleteModel;
                if (this.existenciasModelList) {
                    deleteModel = this.existenciasModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-existencias-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'existencias-delete', view: self, error: error});
                    }
                });
            }
        },
		_loadRequiredComponentsData: function(callBack) {
            var self = this;
            var listReady = _.after(1, function(){
                callBack();
            }); 
            var listDataReady = function(componentName, model){
                self[componentName] = model;
                listReady();
            };
				App.Utils.getComponentList('productoComponent',listDataReady);
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-existenciasForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-existencias-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-existencias-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-existencias-save', {view: this, model : model});
                this.currentExistenciasModel.set(model);
                this.currentExistenciasModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-existencias-save', {model: self.currentExistenciasModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'existencias-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({existenciass: self.existenciasModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({existencias: self.currentExistenciasModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				    ,producto: self.productoComponent
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ExistenciasController;
});
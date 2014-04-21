define(['controller/selectionController', 'model/cacheModel', 'model/bodegaMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/bodegaComponent',
 'component/existenciasComponent'
 
 ],function(SelectionController, CacheModel, BodegaMasterModel, CRUDComponent, TabController, BodegaComponent,
 ExistenciasComponent
 ) {
    App.Component.BodegaMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('bodegaMaster');
            var uComponent = new BodegaComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-bodega-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-bodega-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-bodega-list', function() {
                self.hideChilds();
            });
            Backbone.on('bodega-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'bodega-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-bodega-save', function(params) {
                self.model.set('bodegaEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var existenciasModels = self.existenciasComponent.componentController.existenciasModelList;
                self.model.set('listExistencias', []);
                self.model.set('createExistencias', []);
                self.model.set('updateExistencias', []);
                self.model.set('deleteExistencias', []);
                for (var i = 0; i < existenciasModels.models.length; i++) {
                    var m = existenciasModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createExistencias').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateExistencias').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < existenciasModels.deletedModels.length; i++) {
                    var m = existenciasModels.deletedModels[i];
                    self.model.get('deleteExistencias').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'bodega-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Existencias", name: "existencias", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.BodegaMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.existenciasComponent = new ExistenciasComponent();
                    self.existenciasModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ExistenciasModel), self.model.get('listExistencias'));
                    self.existenciasComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ExistenciasModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ExistenciasModel, App.Model.ExistenciasList, self.existenciasModels)
                    });
                    self.existenciasComponent.render(self.tabs.getTabHtmlId('existencias'));
                    Backbone.on(self.existenciasComponent.componentId + '-post-existencias-create', function(params) {
                        params.view.currentExistenciasModel.setCacheList(params.view.existenciasModelList);
                    });
                    self.existenciasToolbarModel = self.existenciasComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.existenciasComponent.setToolbarModel(self.existenciasToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'bodega-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.BodegaMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.BodegaMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.BodegaMasterComponent;
});
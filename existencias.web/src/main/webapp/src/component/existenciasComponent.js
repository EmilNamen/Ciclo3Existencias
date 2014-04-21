define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/existenciasModel', 'controller/existenciasController'], function() {
    App.Component.ExistenciasComponent = App.Component._CRUDComponent.extend({
        name: 'existencias',
        model: App.Model.ExistenciasModel,
        listModel: App.Model.ExistenciasList,
        controller : App.Controller.ExistenciasController
    });
    return App.Component.ExistenciasComponent;
});
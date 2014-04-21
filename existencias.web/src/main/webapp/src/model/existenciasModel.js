define(['model/_existenciasModel'], function() {
    App.Model.ExistenciasModel = App.Model._ExistenciasModel.extend({

    });

    App.Model.ExistenciasList = App.Model._ExistenciasList.extend({
        model: App.Model.ExistenciasModel
    });

    return  App.Model.ExistenciasModel;

});
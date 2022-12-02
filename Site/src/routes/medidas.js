var express = require("express");
var router = express.Router();

var medidaController = require("../controllers/medidaController");

router.get("/ultimasCpu/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasCpu(req, res);
});

router.get("/tempo-realCpu/:serialNumber", function (req, res) {
    medidaController.buscarMedidasEmTempoRealCpu(req, res);
});

router.get("/ultimasRam/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasRam(req, res);
});

router.get("/tempo-realRam/:serialNumber", function (req, res) {
    medidaController.buscarMedidasEmTempoRealRam(req, res);
});

router.get("/ultimasDisco/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasDisco(req, res);
});

router.get("/maxDisco/:serialNumber", function (req, res) {
    medidaController.buscarMaxDisco(req, res);
});

router.get("/maxRam/:serialNumber", function (req, res) {
    medidaController.buscarMaxRam(req, res);
});

router.get("/ultimasProcCpu/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasProcCpu(req, res);
});

router.get("/tempo-realProcCpu/:serialNumber", function (req, res) {
    medidaController.buscarMedidasEmTempoRealProcCpu(req, res);
});

router.get("/ultimasProcRam/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasProcRam(req, res);
});

router.get("/tempo-realProcRam/:serialNumber", function (req, res) {
    medidaController.buscarMedidasEmTempoRealProcRam(req, res);
});



module.exports = router;
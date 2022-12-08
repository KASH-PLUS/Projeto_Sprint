import requests

def criarCard(phaseId, componente, serialNumber, registroAceitavel, registroObtido, dataHora):

    registroObtido = str(registroObtido)

    url = "https://api.pipefy.com/graphql"

    data = {"query":  "mutation{ createCard( input: { pipe_id: \"302821637\" phase_id: \"" + phaseId +"\" fields_attributes: [ {field_id: \"aviso\", field_value: \"Registro " + componente +" fora do ideal\"} {field_id: \"serial_number\", field_value: \"Máquina: " + serialNumber + "\"} {field_id: \"uso\", field_value: \"Registro aceitável: " + registroAceitavel + "\nRegistro obtido: " + registroObtido + "\"} {field_id: \"data_hora\", field_value: \"" + dataHora +"\"} ] } ) {clientMutationId card {id title }}}"}

    headers = {
        "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyIjp7ImlkIjozMDIxNzYyNTMsImVtYWlsIjoiMjIyLTFjY28tZ3J1cG8xMEBiYW5kdGVjLmNvbS5iciIsImFwcGxpY2F0aW9uIjozMDAyMTQwMDV9fQ.zV0vqSFmSDOMiHXQ2QsOhfjt_khTh5EJ_0myF0dbD8dzebwTL-nJ2wo7HR-sIrG6mkR0RdZV7uEIdY-c4aUfww",
        "Content-Type": "application/json"}
            

    response = requests.request("POST", url, json=data, headers=headers)
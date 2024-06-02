import spotipy
from spotipy.oauth2 import SpotifyClientCredentials
import requests
import csv
import os

CLIENT_ID = 'seu_client_id'
CLIENT_SECRET = 'seu_client_secret'

def obter_musicas_taylor_swift():
    # Configuração do cliente de autenticação
    client_credentials_manager = SpotifyClientCredentials(client_id=CLIENT_ID, client_secret=CLIENT_SECRET)
    sp = spotipy.Spotify(client_credentials_manager=client_credentials_manager)

    artist_id = '06HL4z0CvFAxyc27GXpf02'  # ID do artista Taylor Swift no Spotify

    # Obter os álbuns do artista
    albums = sp.artist_albums(artist_id, album_type='album')

    # Criar o diretório para salvar as imagens
    os.makedirs("downloads", exist_ok=True)

    # Criar o arquivo CSV para escrever as informações das músicas
    with open('musicas_taylor_swift.csv', 'w', newline='', encoding='utf-8') as file:
        writer = csv.writer(file)
        writer.writerow(['Nome', 'Nome da Imagem', 'Nome do Álbum'])

        for album in albums['items']:
            album_name = album['name']
            album_id = album['id']

            # Obter as faixas do álbum
            tracks = sp.album_tracks(album_id)

            for track in tracks['items']:
                track_name = track['name']
                track_id = track['id']

                # Obter a URL da imagem da música
                track_info = sp.track(track_id)
                image_url = track_info['album']['images'][0]['url']

                # Nome do arquivo de imagem
                image_filename = f"{album_name}_{track_name}.jpg"

                # Gravar as informações no arquivo CSV
                writer.writerow([track_name, image_filename, album_name])

                # Fazer o download da imagem
                fazer_download_imagem(image_url, image_filename)

                print(f"Faixa: {track_name}")
                print(f"URL da Imagem: {image_url}")
                print(f"Nome da Imagem: {image_filename}")
                print(f"Nome do Álbum: {album_name}")
                print("Informações gravadas no arquivo CSV. Imagem baixada com sucesso.")
                print()

def fazer_download_imagem(url, nome_arquivo):
    try:
        response = requests.get(url)
        response.raise_for_status()

        with open("downloads/" + nome_arquivo, "wb") as arquivo:
            arquivo.write(response.content)
    except requests.exceptions.HTTPError as errh:
        print(f"Erro HTTP: {errh}")
    except requests.exceptions.RequestException as err:
        print(f"Erro de conexão: {err}")

# Exemplo de uso
obter_musicas_taylor_swift()

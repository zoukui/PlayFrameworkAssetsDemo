## Requirements:

play framework >= 2.2

## Howto

For production mode:

```shell
PLAY_PRODUCTION=1 play
```
Then clean and compile. In html there are outputs like:
```html
<script src="http://static.example.com/javascripts/jquery-1.9.0.min.v5c6aec8.js" type="text/javascript"></script>
```
The "5c6aec8" is got by command:
```shell
git log -n1 --pretty=%%h public/javascripts/jquery-1.9.0.min.js
```
You can also change it to other commands, such as md5sum. Please modify the file **AssetsPath.scala**. This command only runs at compilation.
package io.github.jakejmattson.imagesorter

import java.awt.image.*
import java.io.File
import javax.imageio.ImageIO

fun main() {
	val input = File("input.png")
	val output = File("output.png")
	val image = ImageIO.read(input)!!

	val newImage =
		with (BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)) {
			graphics.drawImage(image, 0, 0, null)
			val pixels = (raster.dataBuffer as DataBufferInt).data.sorted().toIntArray()
			setRGB(0, 0, width, height, pixels, 0, width)
			this
		}

	ImageIO.write(newImage, output.extension, output)
}